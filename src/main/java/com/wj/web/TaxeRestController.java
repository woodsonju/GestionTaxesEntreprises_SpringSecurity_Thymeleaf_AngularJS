package com.wj.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wj.dao.EntrepriseRepository;
import com.wj.dao.entities.Entreprise;

/*
 * Un service web rest RestFull : MVC coté serveur
 * Création d'un service Rest Web MVC avec spring
 * on utilise la notation @RestController
 * Dans les méthodes, On ne retourne plus la vue
 * On retourne les resultats en format JSON au client.
 * L'api rest full va permettre de créer une application web mvc 
 * coté client
 * 
 */
@RestController 
public class TaxeRestController {
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@RequestMapping("/listEntreprises")
	public Page<Entreprise> list(
			@RequestParam(name="mc", defaultValue="")String mc, 
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="2")int size) {
		
		 Page<Entreprise> entreprises = entrepriseRepository.chercher(
				 "%"+mc+"%", 
				 new PageRequest(page, size));
		 
		 return entreprises;
	}
	
}
