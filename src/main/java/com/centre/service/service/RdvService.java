package com.centre.service.service;

import com.centre.service.model.Rdv;
import com.centre.service.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RdvService {

    @Autowired
    private RdvRepository rdvRepository;

    // Récupérer tous les rendez-vous
    public List<Rdv> getAllRdv() {
        return rdvRepository.findAll();
    }

    // Récupérer un rendez-vous par ID
    public Optional<Rdv> getRdvById(Long id) {
        return rdvRepository.findById(id);
    }

    // Créer un rendez-vous
    public Rdv createRdv(Rdv newRdv) {
        newRdv.setDateEnvoi(new Date()); // Date d'envoi automatique
        return rdvRepository.save(newRdv);
    }

    // Mettre à jour un rendez-vous
    public Rdv updateRdv(Long id, Rdv updatedRdv) {
        Optional<Rdv> existingRdv = rdvRepository.findById(id);
        if (existingRdv.isPresent()) {
            Rdv rdv = existingRdv.get();
            rdv.setDateSouhaitee(updatedRdv.getDateSouhaitee());
            rdv.setDescription(updatedRdv.getDescription());
            rdv.setStatus(updatedRdv.getStatus());
            rdv.setTypeProbleme(updatedRdv.getTypeProbleme());
            return rdvRepository.save(rdv);
        }
        return null;
    }

    // Supprimer un rendez-vous
    public boolean deleteRdv(Long id) {
        if (rdvRepository.existsById(id)) {
            rdvRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
