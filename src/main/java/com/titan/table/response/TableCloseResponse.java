package com.titan.table.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.titan.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableCloseResponse {

    private Long id;
    private Double costs;
    private Integer numberOfPeople;
    private List<ProductEntity> products;

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
