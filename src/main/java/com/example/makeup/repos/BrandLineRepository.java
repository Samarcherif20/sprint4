package com.example.makeup.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.makeup.entities.BrandLine;

public interface BrandLineRepository extends JpaRepository<BrandLine, Long> {
}
