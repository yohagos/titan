package com.titan.table.response;

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
public class TableAddProductResponse {

    private Long id;
    private List<ProductEntity> products;
    private Double costs;
}
