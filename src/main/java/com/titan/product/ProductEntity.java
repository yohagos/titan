package com.titan.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.titan.product.category.ProductCategoryEntity;
import com.titan.product.stock.ProductsStockEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductCategoryEntity category;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductsStockEntity> components;

    public ProductEntity(
            String name,
            Double price,
            ProductCategoryEntity category
    ) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Product=[id=%d, name=%s, price=%s]",
                getId(), getName(), getPrice().toString());
    }

}
