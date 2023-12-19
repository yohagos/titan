package com.titan.table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.titan.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Integer tableNumber;
    private Double openCosts;
    private Integer numberOfPeople;
    private boolean occupied;
    private LocalDateTime occupiedFrom;
    private LocalDateTime occupiedTill;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<ProductEntity> products;

    TableEntity(Integer tableNumber, Integer numberOfPeople) {
        this.tableNumber = tableNumber;
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public String toString() {
        return String.format("Product=[id=%d, tableNumber=%d, numberOfPeople=%d]",
                getId(), getTableNumber(), getNumberOfPeople());
    }
}
