package com.example.reusebook.Service;
import java.time.Year;

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
     * @param noOfTransaction The number of transactions for the book.
     * @param publicationAge  The age of the publication in years.
     * @return The discounted price as a string.
     */
    private String calculateDiscountPrice(Integer price, int noOfTransaction, int publicationAge) {
        double priceDouble = price.doubleValue();
        double discountedPrice;

        if (publicationAge <= 5) {
            if (noOfTransaction <= 5) {
                discountedPrice = priceDouble * DiscountConstants.DISCOUNT_10.getDiscount();
            } else {
                discountedPrice = priceDouble * DiscountConstants.DISCOUNT_20.getDiscount();
            }
        } else {
            if (noOfTransaction <= 5) {
                discountedPrice = priceDouble * DiscountConstants.DISCOUNT_30.getDiscount();
            } else {
                discountedPrice = priceDouble * DiscountConstants.DISCOUNT_50.getDiscount();
            }
        }

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
    public String getDiscountDetails(int price, int numofTransaction) {
        // Replace 'publicationAge' with the actual age calculation logic
        int publicationAge = calculatePublicationAge();

        return calculateDiscountPrice(price, numofTransaction, publicationAge);
    }

    // Add a method to calculate the publication age based on the book's year of publication
    private int calculatePublicationAge() {
        // Implement the logic to calculate the publication age
        int currentYear = Year.now().getValue();
        // Replace 'yearOfPublication' with the actual year from the book
        int yearOfPublication = 0; // Set to the actual year of publication
        return currentYear - yearOfPublication;
    }
}
