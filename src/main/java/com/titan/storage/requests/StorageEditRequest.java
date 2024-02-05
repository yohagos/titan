package com.titan.storage.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageEditRequest {

    private String name;
    private Double pricePerBottle;
    private Integer stockOfBottles;
    private String unit;
    private Double measurement;
    private Double currentStock;
    private Integer criticalStockOfBottles;
}
