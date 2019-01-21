package com.wj;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wj.dao.EntrepriseRepository;
import com.wj.dao.TaxeRepository;
import com.wj.dao.entities.Entreprise;
import com.wj.dao.entities.IR;
import com.wj.dao.entities.TVA;
import com.wj.dao.entities.Taxe;

@SpringBootApplication
public class TaxesApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TaxesApplication.class, args);
	}

	
	//Test de la couche DAO
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private TaxeRepository taxeRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		/*
		Entreprise ent1 = entrepriseRepository.save(new Entreprise("R1D1", "r1@gmail.com", "SARL"));
		Entreprise ent2 = entrepriseRepository.save(new Entreprise("R2E2", "r2@gmail.com", "SARL"));
		Entreprise ent3 = entrepriseRepository.save(new Entreprise("R3F3", "r3@gmail.com", "SARL"));
		
		taxeRepository.save(new TVA(new Date(), 900, "TVA Habitation", ent1));
		taxeRepository.save(new TVA(new Date(), 400, "TVA Voiture", ent1));
		taxeRepository.save(new IR(new Date(), 450, "TVA 2016", ent2));
		taxeRepository.save(new IR(new Date(), 700, "IR 2017", ent3));
*/

	}

}

