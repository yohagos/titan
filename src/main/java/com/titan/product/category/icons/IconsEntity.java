package com.titan.product.category.icons;

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
public class IconsEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String label;
    private String name;


    @Override
    public String toString() {
        return String.format("Icon=[id=%d, label=%s, name=%s]", getId(), getLabel(), getName());
    }
}
