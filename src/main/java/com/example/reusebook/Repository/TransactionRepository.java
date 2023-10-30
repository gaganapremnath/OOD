package com.example.reusebook.Repository;

import java.util.List;

import com.example.reusebook.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Custom query method to find all transactions related to a specific book based on its ID.
    List<Transaction> findByBookId(Long bookId);

    // Custom native SQL query to find the latest transaction for a specific book based on its ID.
    @Query(value = "SELECT t.* FROM transactions t WHERE book_id = ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Transaction find_LatestTransaction_ByBookId(Long bookId);

    // Custom native SQL query to count the number of transactions of type 2 (e.g., selling) for a specific book based on its ID.
    @Query(value = "SELECT count(*) FROM transactions t INNER JOIN books b ON b.id = t.book_id WHERE book_id = ?1 AND type_id = 2", nativeQuery = true)
    int count_SellTransactions_ByBookId(Long bookId);

}
