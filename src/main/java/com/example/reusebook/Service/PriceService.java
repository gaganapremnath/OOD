package com.example.reusebook.Service;

import com.example.reusebook.Model.Price;
import com.example.reusebook.Repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    /**
     * Get a list of all prices for a book by its ID.
     *
     * @param bookId The ID of the book to retrieve prices for.
     * @return ResponseEntity with the list of prices and HTTP status.
     */
    public ResponseEntity<List<Price>> getAllPricesByBookId(Long bookId) {
        // Retrieve a list of prices associated with the given book ID
        List<Price> prices = priceRepository.findByBookId(bookId);
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    /**
     * Get the latest price for a book by its ID.
     *
     * @param bookId The ID of the book to retrieve the latest price for.
     * @return ResponseEntity with the latest price and HTTP status.
     */
    public ResponseEntity<Price> getPriceByBookId(Long bookId) {
        // Retrieve the latest price associated with the given book ID
        Price price = priceRepository.find_LatestPrice_ByBookId(bookId);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    /**
     * Update the price information for a book.
     *
     * @param priceId The ID of the price to be updated.
     * @param priceR  The updated price information.
     * @return ResponseEntity with the updated price or an error message.
     */
    public ResponseEntity<Object> updatePrice(long priceId, Price priceR) {
        // Find the price by its ID
        Optional<Price> price = priceRepository.findById(priceId);

        if (price.isPresent()) {
            // If the price exists, update its information
            Price p = price.get();
            if (isValidPrice(priceR.getPrice())) {
                p.setPrice(priceR.getPrice());
                return new ResponseEntity<>(priceRepository.save(p), HttpStatus.OK);
            } else {
                // Invalid price data
                return new ResponseEntity<>("Invalid Price Value", HttpStatus.BAD_REQUEST);
            }
        } else {
            // Price with the given ID not found
            return new ResponseEntity<>("Price Id not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Validates the price value.
     *
     * @param price The price value to validate.
     * @return True if the price value is valid; otherwise, false.
     */
    private boolean isValidPrice(String price) {
        return price != null && !price.isBlank() && !price.isEmpty();
    }
}
