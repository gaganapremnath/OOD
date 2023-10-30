package com.example.reusebook.Repository;

import com.example.reusebook.Model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "SELECT * FROM Prices p inner join Books b ON b.id = p.book_id and p.book_id = ?1 order by p.id desc LIMIT 1", nativeQuery = true)
    Price findByIdOrderByIdDesc(Long bookId);

    List<Price> findByBookId(Long bookId);
    @Query(value = "SELECT * FROM Prices p order by p.id desc LIMIT 1", nativeQuery = true)
    Price findBy_BookId_OrderBy_LevelDesc(Long bookId);
}