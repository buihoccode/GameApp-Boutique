package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


public class JeuCreateDTO {
	
	private String titre;
	private String plateforme;
	private Double prix;
	private Integer nbLicence;
	private Boolean indie;
	private Long studioId;
	
	@Min(3)
	@Max(18)
	private Integer ageMinimum;

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

	public Long getStudioId() {
		return studioId;
	}

	public void setStudioId(Long studioId) {
		this.studioId = studioId;
	}

	public Integer getAgeMinimum() {
		return ageMinimum;
	}

	public void setAgeMinimum(Integer ageMinimum) {
		this.ageMinimum = ageMinimum;
	}
	
	

}
