package com.titan.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.titan.product.category.ProductCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductUpdateRequest {

    private Long id;
    private String name;
    private Double price;
    private ProductCategoryEntity category;
}
