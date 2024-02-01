package com.titan.product.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.titan.product.category.icons.IconsEntity;
import jakarta.persistence.*;
import lombok.*;

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
    private double measurement;
    private String unit;
    private String color;

    @ManyToOne
    private IconsEntity icon;

    ProductCategoryEntity(String name, double measure, String unit, String color, IconsEntity icon) {
        categoryName = name;
        measurement = measure;
        this.unit = unit;
        this.color = color;
        this.icon = icon;
    }

}
