package com.example.makeup.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.makeup.dto.MakeupDTO;
import com.example.makeup.entities.BrandLine;
import com.example.makeup.entities.Makeup;
import com.example.makeup.repos.BrandLineRepository;
import com.example.makeup.repos.MakeupRepository;

@Service
public class MakeupServiceImpl implements MakeupService {
    
    @Autowired

    private final MakeupRepository makeupRepository;
    private final BrandLineRepository brandLineRepository;
    @Autowired
    ModelMapper modelMapper;


    @Autowired
    public MakeupServiceImpl(MakeupRepository makeupRepository, BrandLineRepository brandLineRepository) {
        this.makeupRepository = makeupRepository;
        this.brandLineRepository = brandLineRepository;
    
    }
    @Override
    public MakeupDTO saveMakeup(MakeupDTO m) {
        return convertEntityToDto(makeupRepository.save(convertDtoToEntity(m)));
    }

    @Override
    public MakeupDTO updateMakeup(MakeupDTO m) {
        return convertEntityToDto(makeupRepository.save(convertDtoToEntity(m)));
    }

    @Override
    public void deleteMakeup(Makeup m) {
        makeupRepository.delete(m);
    }

    @Override
    public void deleteMakeupById(Long id) {
        makeupRepository.deleteById(id);
    }

    @Override
    public MakeupDTO getMakeup(Long id) {
        return convertEntityToDto(makeupRepository.findById(id).get());
    }

    @Override
    public List<MakeupDTO> getAllMakeup() {
    	 return makeupRepository.findAll().stream()
                 .map(this::convertEntityToDto)
                 .collect(Collectors.toList());
    }

    // Pagination method corrected:
    @Override
    public Page<Makeup> getAllMakeupParPage(int page, int size) {
        return makeupRepository.findAll(PageRequest.of(page, size));
    }


	@Override
	public List<Makeup> findByNom(String nom) {
		
		return makeupRepository.findByNom(nom) ;
	}

	@Override
	public List<Makeup> findByNomContains(String nom) {
		return makeupRepository.findByNomContains(nom) ;

	}

	@Override
	public List<Makeup> findByNomPrix(String nom, Double prix) {
		return makeupRepository.findByNomPrix(nom, prix);}

	@Override
	public List<Makeup> findByBrand(BrandLine brandLine) {
		return makeupRepository.findByBrand(brandLine);

	}

	@Override
	public List<Makeup> findByBrandLineIdLigneMarque(Long id) {
		return makeupRepository.findByBrandLineIdLigneMarque(id) ;

	}

	@Override
	public List<Makeup> findByOrderByNomAsc() {
		return makeupRepository.findByOrderByNomAsc();

	}

	@Override
	public List<Makeup> trierMakeupNomPrix() {
		return makeupRepository.trierMakeupNomsPrix();

	}
	@Override
	public List<BrandLine> getAllBrandLines() {
	    return brandLineRepository.findAll();
	}
	@Override
	public MakeupDTO convertEntityToDto(Makeup makeup) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		MakeupDTO makeupDTO = modelMapper.map(makeup, MakeupDTO.class);
	    
	    if (makeup.getBrandLine() != null) {
	        makeupDTO.setNomLigne(makeup.getBrandLine().getNomLigne());
	    } else {
	        makeupDTO.setNomLigne("Inconnu");
	    }

	    return makeupDTO;
	}
		
		
	   /* MakeupDTO makeupDTO = new MakeupDTO();
	    makeupDTO.setIdMakeup(makeup.getId());
	    makeupDTO.setMarque(makeup.getMarque());
	    makeupDTO.setNom(makeup.getNom());
	    makeupDTO.setPrix(makeup.getPrix());
	    makeupDTO.setType(makeup.getType());
	    makeupDTO.setBrandLine(makeup.getBrandLine());
	    
	    return makeupDTO;*/
		/* String nomLigne = (makeup.getBrandLine() != null) ? makeup.getBrandLine().getNomLigne() : "Inconnu";
		    
		    return MakeupDTO.builder()
		            .idMakeup(makeup.getId())
		            .marque(makeup.getMarque())
		            .nom(makeup.getNom())
		            .prix(makeup.getPrix())  // Vous pouvez décommenter cette ligne si vous souhaitez inclure le prix
		            .type(makeup.getType())
		            .brandLine(makeup.getBrandLine())
		            .nomLigne(nomLigne)
		            .build();*/
		
	@Override
	public Makeup convertDtoToEntity(MakeupDTO makeupDto) {
		Makeup makeup = new Makeup();
		makeup = modelMapper.map(makeupDto, Makeup.class);
	    /*makeup.setId(makeupDto.getIdMakeup());
	    makeup.setMarque(makeupDto.getMarque());
	    makeup.setPrix(makeupDto.getPrix());
	    makeup.setNom(makeupDto.getNom());
	    makeup.setType(makeupDto.getType());
	    makeup.setBrandLine(makeupDto.getBrandLine());*/
	    
	    return makeup;
	}


}
