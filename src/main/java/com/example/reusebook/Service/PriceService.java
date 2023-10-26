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

    public ResponseEntity<List<Price>> getAllPricesByBookId(Long bookId) {
        List<Price> prices = priceRepository.findByBookId(bookId);
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    public ResponseEntity<Price> getPriceByBookId(Long bookId) {
        Price prices = priceRepository.findByBookIdOrderByLevelDesc(bookId);
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    public ResponseEntity<Object> updatePrice(long priceId, Price priceR) {
        Optional<Price> price = priceRepository.findById(priceId);
        if(price.isPresent()){
            Price p = price.get();
            if(priceR.getPrice() != null && !priceR.getPrice().isBlank() && !priceR.getPrice().isEmpty()){
                p.setPrice(priceR.getPrice());
                return new ResponseEntity<>(priceRepository.save(p), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Bad Request for price",HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>("Price Id not found",HttpStatus.NOT_FOUND);
        }
    }
}
