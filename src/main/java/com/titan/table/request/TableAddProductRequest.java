package com.titan.table.request;

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
public class TableAddProductRequest {

    private Long id;
    private List<ProductEntity> products;
}
