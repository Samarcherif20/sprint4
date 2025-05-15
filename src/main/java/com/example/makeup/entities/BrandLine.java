package com.example.makeup.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BrandLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneMarque;  
    private String nomLigne;      
    private String description;   
    private String publicCible;   
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dateLancement; 
    @JsonIgnore
    @OneToMany(mappedBy = "brandLine")
    private List<Makeup> makeups; 
    
   
}












