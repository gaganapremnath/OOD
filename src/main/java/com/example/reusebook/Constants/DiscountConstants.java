package com.example.reusebook.Constants;

/**
 * An enum that defines various Discount and Transaction constants.
 */

public enum DiscountConstants {
    // Discount constants
    DISCOUNT_10(0.9),
    DISCOUNT_20(0.8),
    DISCOUNT_25(0.75),
    DISCOUNT_30(0.7),
    DISCOUNT_50(0.5),

    // Transaction types
    BOUGHT("1"),
    SELL("2");

    private final String value; // For constants that are strings
    private final double discount; // For constants that are doubles

    DiscountConstants(String value) {
        this.value = value;
        this.discount = 0.0; // Not applicable for string constants
    }

    DiscountConstants(double discount) {
        this.value = null; // Not applicable for double constants
        this.discount = discount;
    }

    public String getValue() {
        return value;
    }

    public double getDiscount() {
        return discount;
    }
}
