package com.example.reusebook.Service;

import com.example.reusebook.Model.Transaction;
import com.example.reusebook.Pojo.TransactionPojo;
import com.example.reusebook.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    private TransactionPojo convertTransactionToTransactionPojo(Transaction transaction){
        TransactionPojo temp = new TransactionPojo(transaction.getId(),transaction.getBook().getTitle(),transaction.getBook().getIsbn(),transaction.getBook().getEdition(),transaction.getStudent().getName(),transaction.getPrice().getPrice(),transaction.getType().getName());
        return temp;
    }
    private List<TransactionPojo> convertListToResponse(List<Transaction> transactions){
        List<TransactionPojo> response = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            TransactionPojo temp = convertTransactionToTransactionPojo(transactions.get(i));
            response.add(temp);
        }
        System.out.println(response);
        return response;
    }

    public ResponseEntity<Object> getAllTransactionByBookId(Long bookId) {
        List<Transaction> transactions = transactionRepository.findByBookId(bookId);
        List<TransactionPojo> response = convertListToResponse(transactions);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getTransactionByBookId(Long bookId) {
        TransactionPojo response = convertTransactionToTransactionPojo(transactionRepository.findTransactionByBookId(bookId));
        System.out.println(response);
        return new ResponseEntity<>(response.toString(),HttpStatus.OK);
    }
}
