package com.example.reusebook.Repository;

import java.util.List;

import com.example.reusebook.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByBookId(Long bookId);

    @Query(value = "SELECT t.* FROM transactions t where book_id = ?1 Order by id DESC LIMIT 1", nativeQuery = true)
    Transaction findTransactionByBookId(Long bookId);
    @Query(value = "SELECT count(*) FROM transactions t INNER JOIN books b ON b.id = t.book_id where book_id = ?1 and type_id = 2", nativeQuery = true)
    int getSellCount(Long bookId);



}
