package com.titan.storage.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageAddRequest {

    private String name;
    private Double pricePerBottle;
    private Integer stockOfBottles;
    private String unit;
    private Double measurement;
    private Double currentStock;
    private Integer criticalStockOfBottles;

}
