package com.centre.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centre.service.model.Personne;
import com.centre.service.repository.PersonneRepository;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    @Autowired
    private PersonneRepository personneRepository;

    // Méthode pour récupérer toutes les personnes
    @GetMapping
    public ResponseEntity<?> getAllPersonnes() {
        List<Personne> personnes = personneRepository.findAll();
        if (personnes.isEmpty()) {
            return ResponseEntity.status(404).body("Aucune personne trouvée.");
        }
        return ResponseEntity.ok(personnes);
    }

    // Méthode pour récupérer une personne par son ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonneById(@PathVariable Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        if (personne.isPresent()) {
            return ResponseEntity.ok(personne.get());
        } else {
            return ResponseEntity.status(404).body("Personne avec l'ID " + id + " non trouvée.");
        }
    }

    // Méthode pour supprimer une personne par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonne(@PathVariable Long id) {
        if (!personneRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Personne avec l'ID " + id + " non trouvée.");
        } else {
            personneRepository.deleteById(id);
            return ResponseEntity.ok("Personne supprimée avec succès !");
        }
    }

    // Méthode pour créer une nouvelle personne
    @PostMapping
    public ResponseEntity<?> createPersonne(@RequestBody Personne newPersonne) {
        try {
            Personne savedPersonne = personneRepository.save(newPersonne);
            return ResponseEntity.ok(savedPersonne);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la création de la personne.");
        }
    }

    // Méthode pour mettre à jour une personne
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonne(@PathVariable Long id, @RequestBody Personne updatedPersonne) {
        Optional<Personne> existingPersonne = personneRepository.findById(id);
        if (existingPersonne.isPresent()) {
            Personne personne = existingPersonne.get();
            personne.setNom(updatedPersonne.getNom());
            personne.setEmail(updatedPersonne.getEmail());
            personne.setMotDePasse(updatedPersonne.getMotDePasse());
            personne.setAdresse(updatedPersonne.getAdresse());
            personne.setNumTel(updatedPersonne.getNumTel());
            personne.setRole(updatedPersonne.getRole());
            personneRepository.save(personne);
            return ResponseEntity.ok(personne);
        } else {
            return ResponseEntity.status(404).body("Personne avec l'ID " + id + " non trouvée.");
        }
    }
}
