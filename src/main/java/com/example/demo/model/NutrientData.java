package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nutrient_data")
public class NutrientData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nutrientName;
    private int nutrientValue;

}
