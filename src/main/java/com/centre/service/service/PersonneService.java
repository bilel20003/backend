package com.centre.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.centre.service.model.Personne;
import com.centre.service.repository.PersonneRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;  // Accès à la base de données

    // Méthode pour récupérer toutes les personnes
    public List<Personne> findAll() {
        return personneRepository.findAll();  // Appel au repository pour récupérer toutes les personnes
    }

    // Méthode pour sauvegarder une nouvelle personne
    public Personne save(Personne personne) {
        return personneRepository.save(personne);  // Sauvegarde la personne dans la base de données
    }

    // Méthode pour récupérer une personne par son ID (au cas où tu voudrais l'ajouter dans ton contrôleur)
    public Optional<Personne> findById(Long id) {
        return personneRepository.findById(id);
    }
}
