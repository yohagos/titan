package com.titan.product.stock;

public class UnitConverter {

    public enum Unit {
        CL(0.01),
        ML(0.001),
        L(1.0),
        G(1.0),
        MG(0.001);

        private final double conversionFactor;

        Unit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double convertTo(double value, Unit targetUnit) {
            return  value * this.conversionFactor / targetUnit.conversionFactor;
        }
    }

    public static double convert(double value, Unit sourceUnit, Unit targetUnit) {
        return sourceUnit.convertTo(value, targetUnit);
    }
}
