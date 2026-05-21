package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String siege; 
    
    @OneToMany(mappedBy = "studio")
    private List<Jeu> jeux;

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

	public String getSiege() {
		return siege;
	}

	public void setSiege(String siege) {
		this.siege = siege;
	}

	public List<Jeu> getJeux() {
		return jeux;
	}

	public void setJeux(List<Jeu> jeux) {
		this.jeux = jeux;
	}

}