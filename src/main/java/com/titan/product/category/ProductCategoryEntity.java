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
    private Double measurement;
    private String unit;

    ProductCategoryEntity(String name, Double measure, String unit) {
        categoryName = name;
        measurement = measure;
        this.unit = unit;
    }

}
