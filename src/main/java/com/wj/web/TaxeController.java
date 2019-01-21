package com.wj.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wj.dao.EntrepriseRepository;
import com.wj.dao.TaxeRepository;
import com.wj.dao.entities.Entreprise;
import com.wj.dao.entities.Taxe;

@Controller
public class TaxeController {
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private TaxeRepository taxeRepository;
	
	@RequestMapping("/entreprises")
	/*
	 * En mettant page et size en parametre, je dis à spring, au dispatcherServlet
	 * de me lire les  parametres url qui s'appelle page et size
	 * Donc ici, il va venir me lire les paramètre que j'aurais placé dans 
	 * l'url (http://localhost:8080/entreprises?page=0&size=3)
	 */
	public String index(Model model, 
			@RequestParam(name="page", defaultValue="0")int page, 
			@RequestParam(name="size", defaultValue="2")int size,
			@RequestParam(name="mc", defaultValue="")String mc) 
	{
		
		/*List<Entreprise> listEntreprises = entrepriseRepository.findAll();
		model.addAttribute("listEntreprises", listEntreprises);*/
		
		//On veut afficher page par page
		/*Page<Entreprise> pageEntreprises = entrepriseRepository.findAll(
				new PageRequest(page, size));*/
		Page<Entreprise> pageEntreprises = entrepriseRepository.chercher("%"+mc+"%", 
				new PageRequest(page, size));
		//getContent nous permet d'avoir la liste de la page retournée
		model.addAttribute("listEntreprises", pageEntreprises.getContent());
		
		//On récupère le nombre de pages afin de l'afficher 
		int[] pages = new int[pageEntreprises.getTotalPages()];
		model.addAttribute("totalPages", pages);
		
		model.addAttribute("pageCourante", page);
		
		model.addAttribute("mc", mc);
		
		return "entreprise";
	}
	
	@RequestMapping("/formEntreprise")
	public String formEntreprise(Model model) {
		model.addAttribute("entreprise", new Entreprise()); //affiche une valeur par défaut dans le formulaire stockée dans new Entreprise()
		return "formEntreprise";
	}
	
	@RequestMapping(value="/saveEntreprise", method=RequestMethod.POST)
	/*
	 * On declare un objet de type Entreprise pour ne pas récuperer champs par champs
	 */
	public String saveEntreprise(Model model, 
			@Valid Entreprise entreprise, 
			BindingResult bindingRresult) {
		
		if(bindingRresult.hasErrors())
			return "formEntreprise";
		
		entrepriseRepository.save(entreprise);
		
		return "redirect:/entreprises";
	}
	
	@RequestMapping("/taxes")
	public String taxes(Model model, @RequestParam(name="code", defaultValue="-1")Long code) {
		
		
		List<Entreprise> entreprises = entrepriseRepository.findAll();
		model.addAttribute("entreprises", entreprises);
		
		if(code == -1)
			model.addAttribute("taxes", new ArrayList<Taxe>());
		else {
			Entreprise e = new Entreprise();
			e.setCode(code);	
			List<Taxe> taxes = taxeRepository.findByEntreprise(e);
			model.addAttribute("taxes", taxes);
		}
		
		return "taxes";
	}
}
