package com.titan.product.stock.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockAddRequest {

    private String good;
    private String unit;
    private Double measurement;
}
