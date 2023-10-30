package com.example.reusebook.Service;

import com.example.reusebook.Model.Transaction;
import com.example.reusebook.Pojo.TransactionPojo;
import com.example.reusebook.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Convert a Transaction entity to a TransactionPojo for response.
     *
     * @param transaction The Transaction entity to convert.
     * @return A TransactionPojo representing the Transaction entity.
     */
    private TransactionPojo convertTransactionToTransactionPojo(Transaction transaction) {
        return new TransactionPojo(
            transaction.getId(),
            transaction.getBook().getTitle(),
            transaction.getBook().getIsbn(),
            transaction.getBook().getEdition(),
            transaction.getStudent().getName(),
            transaction.getPrice().getPrice(),
            transaction.getType().getName()
        );
    }

    /**
     * Get a list of all transactions for a book by its ID.
     *
     * @param bookId The ID of the book to retrieve transactions for.
     * @return ResponseEntity with the list of transactions as a string and HTTP status.
     */
    public ResponseEntity<Object> getAllTransactionByBookId(Long bookId) {
        List<Transaction> transactions = transactionRepository.findByBookId(bookId);
        List<TransactionPojo> response = transactions.stream()
            .map(this::convertTransactionToTransactionPojo)
            .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get the latest transaction for a book by its ID.
     *
     * @param bookId The ID of the book to retrieve the latest transaction for.
     * @return ResponseEntity with the latest transaction as a string and HTTP status.
     */
    public ResponseEntity<Object> getTransactionByBookId(Long bookId) {
        Transaction latestTransaction = transactionRepository.find_LatestTransaction_ByBookId(bookId);
        TransactionPojo response = convertTransactionToTransactionPojo(latestTransaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
