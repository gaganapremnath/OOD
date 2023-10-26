package com.example.reusebook.Repository;

import java.util.List;

import com.example.reusebook.Model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE (LOWER(b.title) LIKE LOWER(CONCAT('%', ?1,'%')) or LOWER(b.edition) LIKE LOWER(CONCAT('%', ?1,'%')) or LOWER(b.isbn) LIKE LOWER(CONCAT('%', ?1,'%'))) and isAvailable = 1 ")
    List<Book> findByTitleContainsOrEditionContainsOrIsbnContainsAndIsAvailable(String searchKey, Pageable pageable);
}
