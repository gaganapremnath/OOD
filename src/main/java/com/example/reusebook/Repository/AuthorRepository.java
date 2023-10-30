package com.example.reusebook.Repository;

import java.util.List;

import com.example.reusebook.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // This method retrieves a list of authors based on a book's ID.
    // It uses Spring Data JPA query method naming conventions.
    List<Author> findByBookId(Long bookId);
}
