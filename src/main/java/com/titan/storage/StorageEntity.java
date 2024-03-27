package com.titan.storage;

import com.titan.product.category.CategoryUnit;
import com.titan.product.stock.UnitConverter;
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

@AllArgsConstructor
@Entity
public class StorageEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double pricePerBottle;
    private Integer stockOfBottles;
    private UnitConverter.Unit unit;
    private Double measurement;
    private Double currentStock;
    private Integer criticalStockOfBottles;


    public StorageEntity(String name, Double pricePerBottle, Integer stockOfBottles, UnitConverter.Unit unit, Double measurement, Double currentStock, Integer criticalStockOfBottles) {
        this.name = name;
        this.pricePerBottle = pricePerBottle;
        this.stockOfBottles = stockOfBottles;
        this.unit = unit;
        this.measurement = measurement;
        this.currentStock = currentStock;
        this.criticalStockOfBottles = criticalStockOfBottles;
    }

    public StorageEntity() {}

    @Override
    public String toString() {
        return String.format(
                "Storage=[id=%s, name=%s, pricePerBottle=%s, stockOfBottles=%d, unit=%s, measurement=%s, currentStock=%s, criticalStockOfBottles=%d]",
                getId(), getName(), getPricePerBottle(), getStockOfBottles(), getUnit(), getMeasurement(), getCurrentStock(), getCriticalStockOfBottles()
        );
    }

}
