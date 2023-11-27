package com.example.reusebook.Controller;


import com.example.reusebook.Model.Price;
import com.example.reusebook.Service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/prices") 
public class PriceController {
    @Autowired
    private PriceService priceService;

    @GetMapping("/{bookId}/getAllPrices")
    public ResponseEntity<List<Price>> getAllPricesByBookId(@PathVariable(value = "bookId") Long bookId) {
        return priceService.getAllPricesByBookId(bookId);
    }

    @GetMapping("/{bookId}/getCurrentPrice")
    public ResponseEntity<Price> getPriceByBookId(@PathVariable(value = "bookId") Long bookId) {
        return priceService.getPriceByBookId(bookId);
    }

    @PutMapping("/editPrice/{priceId}")
    public ResponseEntity<Object> updatePrice(@PathVariable("priceId") long priceId, @RequestBody Price priceR) {
        return priceService.updatePrice(priceId,priceR);
    }


}
