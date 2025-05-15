package com.example.makeup.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
public class Makeup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*auto-increment*/
   
        private Long idMakeup;

    @NotBlank(message = "Le nom est obligatoire")
        @Size(min = 4, max = 15, message = "Le nom doit contenir entre 4 et 15 caractères")
        private String nom;

        @NotBlank(message = "La marque est obligatoire")
        private String marque;

        @NotBlank(message = "Le type est obligatoire")
        private String type;

        @NotNull(message = "Le prix est obligatoire")
        @Min(value = 10)
        @Max(value = 10000, message = "Le prix maximum est 10000")
        private Double prix;
  
@ManyToOne
   private BrandLine brandLine;

    public Makeup() {
        super();
    }

    public Makeup(String nom, String marque, String type, Double prix) {
        super();
        this.nom = nom;
        this.marque = marque;
        this.type = type;
        this.prix = prix;
    }

    public Long getIdMakeup() {
        return idMakeup;
    }

    public void setIdMakeup(Long id) {
        this.idMakeup = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Makeup [id=" + idMakeup + ", nom=" + nom + ", marque=" + marque + ", type=" + type + ", prix=" + prix + "]";
    }

	public BrandLine getBrandLine() {
		return brandLine;
	}

	public void setBrandLine(BrandLine brandline) {
		this.brandLine = brandline;
	}
}
