package com.titan.product.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CategoryUnit {
    MG("mg"),
    G("g"),
    CL("cl"),
    ML("ml"),
    L("L");

    @Getter
    private final String unit;
}
