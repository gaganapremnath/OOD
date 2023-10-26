package com.example.reusebook.Repository;

import java.util.List;

import com.example.reusebook.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByBookId(Long bookId);
}
