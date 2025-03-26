package com.centre.service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "personne") // Nom explicite pour la table
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom; // Ajout du prénom
    private String motDePasse;
    private String numTel;

    @Enumerated(EnumType.STRING) // Stocke la valeur sous forme de texte dans la BDD
    private Role role; // Restriction des valeurs possibles

    // Constructeurs
    public Personne() {}

    public Personne(String nom, String prenom, String motDePasse, String numTel, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.numTel = numTel;
        this.role = role;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
