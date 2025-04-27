package com.example.makeup.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.makeup.dto.MakeupDTO;
import com.example.makeup.entities.BrandLine;
import com.example.makeup.entities.Makeup;

public interface MakeupService {

	MakeupDTO updateMakeup(MakeupDTO m);

	void deleteMakeup(Makeup m);

	void deleteMakeupById(Long id);

	MakeupDTO saveMakeup(MakeupDTO makeup);

	MakeupDTO getMakeup(Long id);

	List<MakeupDTO> getAllMakeup();

	Page<Makeup> getAllMakeupParPage(int page, int size);

	List<Makeup> findByNom(String nom);

	List<Makeup> findByNomContains(String nom);

	List<Makeup> findByNomPrix(String nom, Double prix);

	List<Makeup> findByBrand(BrandLine brandLine);

	List<Makeup> findByBrandLineIdLigneMarque(Long id);

	List<Makeup> findByOrderByNomAsc();

	List<Makeup> trierMakeupNomPrix();

	List<BrandLine> getAllBrandLines();

	MakeupDTO convertEntityToDto(Makeup makeup);
    Makeup convertDtoToEntity(MakeupDTO makeupDto);

}
