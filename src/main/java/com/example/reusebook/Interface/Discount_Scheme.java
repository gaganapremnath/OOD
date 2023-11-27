package com.example.reusebook.Interface;

/**
 * An interface for discount strategies applied to transactions.
 */
public interface Discount_Scheme {
    /**
     * Get a discount description for a given price and the number of transactions.
     *
     * @param price           The original price of a transaction.
     * @return A string describing the discount applied.
     */
    String getDiscountDetails(int price);
}
