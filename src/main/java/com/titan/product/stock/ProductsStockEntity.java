package com.titan.product.stock;

import com.titan.product.category.CategoryUnit;
import com.titan.storage.StorageEntity;
import jakarta.persistence.*;
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
public class ProductsStockEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private UnitConverter.Unit unit;
    private Double measurement;
    private StorageEntity good;


    @Override
    public String toString() {
        return String.format(
                "ProductStock=[id=%d, unit=%s, measurement=%s, good=%s]",
                getId(), getUnit(), getMeasurement(), getGood()
        );
    }
}
