package com.example.makeup.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.makeup.entities.BrandLine;
@RepositoryRestResource(path = "brand")
@CrossOrigin("http://localhost:4200/") //pour autoriser angular 
public interface BrandLineRepository extends JpaRepository<BrandLine, Long> {
}
