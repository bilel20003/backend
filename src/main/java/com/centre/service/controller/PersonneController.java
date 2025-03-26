package com.centre.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.centre.service.model.Personne;
import com.centre.service.model.Role;
import com.centre.service.repository.PersonneRepository;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    @Autowired
    private PersonneRepository personneRepository;

    // Récupérer toutes les personnes
    @GetMapping
    public ResponseEntity<?> getAllPersonnes() {
        try {
            List<Personne> personnes = personneRepository.findAll();
            if (personnes.isEmpty()) {
                return ResponseEntity.status(404).body("Aucune personne trouvée.");
            }
            return ResponseEntity.ok(personnes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur interne du serveur lors de la récupération des personnes.");
        }
    }

    // Récupérer une personne par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonneById(@PathVariable Long id) {
        try {
            Optional<Personne> personne = personneRepository.findById(id);
            if (personne.isPresent()) {
                return ResponseEntity.ok(personne.get());
            } else {
                return ResponseEntity.status(404).body("Personne avec l'ID " + id + " non trouvée.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur interne du serveur lors de la récupération de la personne.");
        }
    }

    // Supprimer une personne par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonne(@PathVariable Long id) {
        try {
            if (!personneRepository.existsById(id)) {
                return ResponseEntity.status(404).body("Personne avec l'ID " + id + " non trouvée.");
            } 
            personneRepository.deleteById(id);
            return ResponseEntity.ok("Personne supprimée avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur interne du serveur lors de la suppression de la personne.");
        }
    }

    // Créer une nouvelle personne
    @PostMapping
    public ResponseEntity<?> createPersonne(@RequestBody Personne newPersonne) {
        // Validation du rôle
        if (newPersonne.getRole() == null || !isValidRole(newPersonne.getRole())) {
            return ResponseEntity.status(400).body("Rôle invalide. Les rôles valides sont : CLIENT, GUICHETIER, ADMIN, TECHNICIEN, DIRECTEUR_DACA.");
        }

        try {
            Personne savedPersonne = personneRepository.save(newPersonne);
            return ResponseEntity.ok(savedPersonne);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur interne du serveur lors de la création de la personne.");
        }
    }

    // Mettre à jour une personne
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonne(@PathVariable Long id, @RequestBody Personne updatedPersonne) {
        try {
            Optional<Personne> existingPersonne = personneRepository.findById(id);
            if (existingPersonne.isPresent()) {
                Personne personne = existingPersonne.get();

                // Validation du rôle
                if (updatedPersonne.getRole() == null || !isValidRole(updatedPersonne.getRole())) {
                    return ResponseEntity.status(400).body("Rôle invalide. Les rôles valides sont : CLIENT, GUICHETIER, ADMIN, TECHNICIEN, DIRECTEUR_DACA.");
                }

                // Mise à jour des champs
                personne.setNom(updatedPersonne.getNom());
                personne.setPrenom(updatedPersonne.getPrenom());
                personne.setMotDePasse(updatedPersonne.getMotDePasse());
                personne.setNumTel(updatedPersonne.getNumTel());
                personne.setEmail(updatedPersonne.getEmail()); // Mise à jour de l'email
                personne.setRole(updatedPersonne.getRole());

                personneRepository.save(personne);
                return ResponseEntity.ok(personne);
            } else {
                return ResponseEntity.status(404).body("Personne avec l'ID " + id + " non trouvée.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur interne du serveur lors de la mise à jour de la personne.");
        }
    }

    // Vérifier si un rôle est valide
    private boolean isValidRole(Role role) {
        try {
            Role.valueOf(role.name());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
