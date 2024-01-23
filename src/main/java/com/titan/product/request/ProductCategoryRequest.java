package com.titan.product.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.titan.product.category.icons.IconsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCategoryRequest {

    private String categoryName;
    private double measurement;
    private String unit;
    private String color;
    private Long iconId;
}
