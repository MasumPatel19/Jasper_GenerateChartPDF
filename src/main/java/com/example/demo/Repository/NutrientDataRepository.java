package com.example.demo.Repository;

import com.example.demo.model.NutrientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientDataRepository extends JpaRepository<NutrientData,Integer> {
}
