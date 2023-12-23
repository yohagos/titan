package com.titan.product.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCategoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String categoryName;
    private String measurement;
    private String unit;
    private String color;

    ProductCategoryEntity(String name, String measure, String unit, String color) {
        categoryName = name;
        measurement = measure;
        this.unit = unit;
        this.color = color;
    }

}
