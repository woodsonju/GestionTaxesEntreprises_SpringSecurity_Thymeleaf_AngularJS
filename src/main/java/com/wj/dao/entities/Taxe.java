package com.wj.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_TAXE",
discriminatorType=DiscriminatorType.STRING, length=4)
public abstract  class Taxe implements Serializable{
	
	@Id @GeneratedValue
	private Long numero;
	
	private Date date;
	private double montant;
	private String titre;
	
	@ManyToOne
	@JoinColumn(name="CODE_ENT")
	private Entreprise entreprise;

	public Taxe() {
		super();
	}
	
	public Taxe(Date date, double montant, String titre, Entreprise entreprise) {
		super();
		this.date = date;
		this.montant = montant;
		this.titre = titre;
		this.entreprise = entreprise;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}


}
