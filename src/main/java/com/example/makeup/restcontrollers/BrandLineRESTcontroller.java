package com.example.makeup.restcontrollers;

import com.example.makeup.entities.BrandLine;
import com.example.makeup.repos.BrandLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin(origins = "*")
public class BrandLineRESTcontroller {

    @Autowired
    BrandLineRepository brandlineRepository;

    @GetMapping
    public CollectionModel<EntityModel<BrandLine>> getAllBrands() {
        List<BrandLine> brandLines = brandlineRepository.findAll();

        List<EntityModel<BrandLine>> brandLineResources = brandLines.stream()
            .map(bl -> EntityModel.of(bl,
                linkTo(methodOn(BrandLineRESTcontroller.class).getBrandById(bl.getIdLigneMarque())).withSelfRel()))
            .collect(Collectors.toList());

        return CollectionModel.of(brandLineResources,
            linkTo(methodOn(BrandLineRESTcontroller.class).getAllBrands()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<BrandLine> getBrandById(@PathVariable("id") Long id) {
        BrandLine bl = brandlineRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("BrandLine not found with ID: " + id));

        return EntityModel.of(bl,
            linkTo(methodOn(BrandLineRESTcontroller.class).getBrandById(id)).withSelfRel(),
            linkTo(methodOn(BrandLineRESTcontroller.class).getAllBrands()).withRel("brandLines"));
    }
    @PostMapping
    public EntityModel<BrandLine> createBrandLine(@RequestBody BrandLine newBrandLine) {
        newBrandLine.setIdLigneMarque(null); // ✅ Force Hibernate to treat it as new

        BrandLine savedBrandLine = brandlineRepository.save(newBrandLine);

        return EntityModel.of(savedBrandLine,
            linkTo(methodOn(BrandLineRESTcontroller.class).getBrandById(savedBrandLine.getIdLigneMarque())).withSelfRel(),
            linkTo(methodOn(BrandLineRESTcontroller.class).getAllBrands()).withRel("brandLines"));
    }
    @PutMapping("/{id}")
    public EntityModel<BrandLine> updateBrandLine(@PathVariable("id") Long id, @RequestBody BrandLine updatedBrandLine) {
        BrandLine existingBrand = brandlineRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("BrandLine not found with ID: " + id));

        // Met à jour seulement les champs modifiables
        existingBrand.setNomLigne(updatedBrandLine.getNomLigne());
        existingBrand.setPublicCible(updatedBrandLine.getPublicCible());
        existingBrand.setDateLancement(updatedBrandLine.getDateLancement());
        existingBrand.setDescription(updatedBrandLine.getDescription());

        BrandLine savedBrand = brandlineRepository.save(existingBrand);

        return EntityModel.of(savedBrand,
            linkTo(methodOn(BrandLineRESTcontroller.class).getBrandById(id)).withSelfRel(),
            linkTo(methodOn(BrandLineRESTcontroller.class).getAllBrands()).withRel("brandLines"));
    }

    

}
