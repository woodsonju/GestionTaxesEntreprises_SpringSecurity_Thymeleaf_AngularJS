package com.wj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wj.dao.entities.Entreprise;
import com.wj.dao.entities.Taxe;

public interface TaxeRepository extends JpaRepository<Taxe, Long>{
	
	/*
	 * Ce n'est pas la peine d'utiliser la notation query
	 * car on respect la convention
	 */
	public List<Taxe> findByEntreprise(Entreprise e);
}
