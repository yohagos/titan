package com.titan.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.titan.product.category.ProductCategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategoryEntity category;

}
