package com.example.makeup.dto;


import com.example.makeup.entities.BrandLine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MakeupDTO {
    private Long idMakeup;
    private String marque;
    private String nom;
    private Double prix;
    private String type;
    private BrandLine brandLine;
    private String nomLigne;
}