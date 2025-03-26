package com.centre.service.service;

import com.centre.service.model.Requete;
import com.centre.service.model.EtatRequete;
import com.centre.service.model.Personne;
import com.centre.service.repository.RequeteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RequeteService {

    @Autowired
    private RequeteRepository requeteRepository;

    // Créer une nouvelle requête
    public Requete creerRequete(Personne client, Personne guichetier, String type, String objet, String description) {
        Requete requete = new Requete();
        requete.setClient(client);
        requete.setGuichetier(guichetier);
        requete.setType(type);
        requete.setObjet(objet);
        requete.setDescription(description);
        requete.setEtat(EtatRequete.EN_COURS_DE_TRAITEMENT);  // Exemple d'état initial
        requete.setDate(new Date());

        // Sauvegarder la requête
        return requeteRepository.save(requete);
    }

    // Méthode pour assigner un technicien à une requête
    public Requete assignerTechnicien(Long requeteId, Personne technicien) {
        Requete requete = requeteRepository.findById(requeteId)
                .orElseThrow(() -> new RuntimeException("Requête non trouvée"));

        requete.setTechnicien(technicien);
        return requeteRepository.save(requete);
    }

    // Autres méthodes de service pour traiter les requêtes...
}
