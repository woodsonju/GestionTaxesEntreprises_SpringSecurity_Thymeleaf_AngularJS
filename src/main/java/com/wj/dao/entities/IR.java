package com.wj.dao.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IR")
public class IR extends Taxe{

	public IR() {
	}

	public IR(Date date, double montant, String titre, Entreprise entreprise) {
		super(date, montant, titre, entreprise);
		// TODO Auto-generated constructor stub
	}

	
}
