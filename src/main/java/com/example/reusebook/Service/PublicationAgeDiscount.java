package com.example.reusebook.Service;

import com.example.reusebook.Constants.DiscountConstants;
import com.example.reusebook.Interface.Discount_Scheme;

/**
 * A discount strategy for books based on publication age.
 */
public class PublicationAgeDiscount implements Discount_Scheme {

    /**
     * Calculate the discounted price based on the original price, the number of transactions, and the publication age.
     *
     * @param price           The original price of the book.
     * @return The discounted price as a string.
     */
    private String calculateDiscountPrice(Integer price) {
        double priceDouble = price.doubleValue();
        double discountedPrice = priceDouble * DiscountConstants.DISCOUNT_10.getDiscount();
        return String.valueOf(Math.round(discountedPrice));
    }

    /**
     * Get the discount based on the number of transactions and publication age.
     *
     * @param price           The original price of the book.
     * @param noOfTransaction The number of transactions for the book.
     * @param publicationAge  The age of the publication in years.
     * @return The discounted price as a string.
     */
    @Override
    public String getDiscountDetails(int price) {
        return calculateDiscountPrice(price);
    }

}
