package com.wj.dao.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TVA")
public class TVA extends Taxe{

	public TVA() {

	}

	public TVA(Date date, double montant, String titre, Entreprise entreprise) {
		super(date, montant, titre, entreprise);
	}

	
}
