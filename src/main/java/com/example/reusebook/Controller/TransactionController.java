package com.example.reusebook.Controller;


import com.example.reusebook.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // This means that this class is a Controller
@RequestMapping(path="/transactions") // This means URL's start with /demo (after Application path)

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{bookId}/getAllTransaction")
    public ResponseEntity<Object> getAllTransactionByBookId(@PathVariable(value = "bookId") Long bookId) {
        return transactionService.getAllTransactionByBookId(bookId);
    }

    @GetMapping("/{bookId}/getTransaction")
    public ResponseEntity<Object> getTransactionByBookId(@PathVariable(value = "bookId") Long bookId) {
        return transactionService.getTransactionByBookId(bookId);
    }
}
