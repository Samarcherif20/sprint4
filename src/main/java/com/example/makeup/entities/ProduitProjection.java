package com.example.makeup.entities;


import org.springframework.data.rest.core.config.Projection;
@Projection(name = "nomMakeup", types = { Makeup.class })
public interface ProduitProjection {
public String getNom();
}
