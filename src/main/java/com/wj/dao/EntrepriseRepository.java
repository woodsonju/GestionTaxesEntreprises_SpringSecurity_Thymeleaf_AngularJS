package com.wj.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wj.dao.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{
	@Query("select e from Entreprise e where e.nom like :x")
	Page<Entreprise> chercher(@Param("x")String mc, Pageable pageable);
}
