package com.example.demo.dto;

import java.util.List;

public class JeuResponseDTO {
	private Long id;
    private String titre;
    private String plateforme;
    private Double prix;
    private Integer nbLicence;
    private Boolean disponible;
    private Double prixTotal;
    private Integer ageMinimum;
    
    private String studioDisplay;
    private List<GenreDTO> tags;
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
	public Boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	public Double getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}
	public Integer getAgeMinimum() {
		return ageMinimum;
	}
	public void setAgeMinimum(Integer ageMinimum) {
		this.ageMinimum = ageMinimum;
	}
	public String getStudioDisplay() {
		return studioDisplay;
	}
	public void setStudioDisplay(String studioDisplay) {
		this.studioDisplay = studioDisplay;
	}
	public List<GenreDTO> getTags() {
		return tags;
	}
	public void setTags(List<GenreDTO> tags) {
		this.tags = tags;
	}
    
    

}
