package com.example.reusebook.Repository;

import com.example.reusebook.Model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    // Custom query to find the latest price for a specific book based on its ID.
    @Query(value = "SELECT * FROM Prices p INNER JOIN Books b ON b.id = p.book_id AND p.book_id = ?1 ORDER BY p.id DESC LIMIT 1", nativeQuery = true)
    Price find_LatestPrice_ByBookId(Long bookId);

    // Custom query to find all prices for a specific book based on its ID.
    List<Price> findByBookId(Long bookId);

    // Custom query to find the price with the highest level for a specific book based on its ID.
    @Query(value = "SELECT * FROM Prices p WHERE p.book_id = ?1 ORDER BY p.level DESC LIMIT 1", nativeQuery = true)
    Price findPrice_WithHighestLevel_ByBookId(Long bookId);
}