package com.titan.storage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class StorageEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double pricePerBottle;
    private Integer stockOfBottles;
    private Double currentStock;
    private Integer criticalStockOfBottles;


    @Override
    public String toString() {
        return String.format(
                "Storage=[id=%s, name=%s, pricePerBottle=%s, stockOfBottles=%d, currentStock=%s, criticalStockOfBottles=%d]",
                getId(), getName(), getPricePerBottle(), getStockOfBottles(), getCurrentStock(), getCriticalStockOfBottles()
        );
    }

}
