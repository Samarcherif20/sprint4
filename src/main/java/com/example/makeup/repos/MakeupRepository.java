package com.example.makeup.repos;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.makeup.entities.BrandLine;
import com.example.makeup.entities.Makeup;
@RepositoryRestResource

public interface MakeupRepository extends JpaRepository<Makeup, Long> {
		List<Makeup> findByNom(String nom);
	    List<Makeup> findByNomContains(String nom);
		@Query("select m from Makeup m where m.nom like %:nom% and m.prix > :prix")
		List<Makeup> findByNomPrix(@Param("nom") String nom, @Param("prix") Double prix);
		@Query("select m from Makeup m where m.brandLine = ?1")
		List<Makeup> findByBrand (BrandLine brandLine);
		List<Makeup> findByBrandLineIdLigneMarque(Long id);
		List<Makeup> findByOrderByNomAsc();
		@Query("select m from Makeup m order by m.nom ASC, m.prix DESC")
		List<Makeup> trierMakeupNomsPrix ();

}

