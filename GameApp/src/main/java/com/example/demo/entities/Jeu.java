package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Jeu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titre;
    private String plateforme;
    
    private Double prix; 
    private Integer nbLicence; 
    
    private Boolean indie; 
    
    private LocalDateTime dateAjout;
    private Boolean disponible; 
    
    @Min(3)
    @Max(18)
    private Integer ageMinimum; 
    
    @ManyToOne
    private Studio studio;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "jeu_genre",
        joinColumns = @JoinColumn(name = "jeu_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @PrePersist
    protected void onCreate() {
        this.dateAjout = LocalDateTime.now();
        if (this.disponible == null) {
            this.disponible = true;
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getPlateforme() {
		return plateforme;
	}

	public void setPlateforme(String plateforme) {
		this.plateforme = plateforme;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Integer getNbLicence() {
		return nbLicence;
	}

	public void setNbLicence(Integer nbLicence) {
		this.nbLicence = nbLicence;
	}

	public Boolean getIndie() {
		return indie;
	}

	public void setIndie(Boolean indie) {
		this.indie = indie;
	}

	public LocalDateTime getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(LocalDateTime dateAjout) {
		this.dateAjout = dateAjout;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Integer getAgeMinimum() {
		return ageMinimum;
	}

	public void setAgeMinimum(Integer ageMinimum) {
		this.ageMinimum = ageMinimum;
	}

	public Studio getStudio() {
		return studio;
	}

	public void setStudio(Studio studio) {
		this.studio = studio;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
}