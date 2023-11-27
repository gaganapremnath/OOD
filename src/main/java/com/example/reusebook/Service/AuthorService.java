package com.example.reusebook.Service;

import com.example.reusebook.Model.Author;
import com.example.reusebook.Model.Book;
import com.example.reusebook.Repository.AuthorRepository;
import com.example.reusebook.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Add an author to a book.
     *
     * @param bookId The ID of the book to which the author will be added.
     * @param author The author to be added.
     * @return ResponseEntity with the added author or an error message.
     */
    public ResponseEntity<Object> addAuthor(Long bookId, Author author) {
        // Find the book by its ID
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isEmpty()) {
            // Book ID not found
            return new ResponseEntity<>("Book ID not found", HttpStatus.BAD_REQUEST);
        }

        if (!isValidAuthor(author)) {
            // Invalid author data
            return new ResponseEntity<>("Bad Request for Author", HttpStatus.BAD_REQUEST);
        }

        // If the book exists and the author data is valid, associate the author with the book
        author.setBook(book.get());
        return new ResponseEntity<>(authorRepository.save(author), HttpStatus.CREATED);
    }

    /**
     * Get all authors for a specific book.
     *
     * @param bookId The ID of the book.
     * @return ResponseEntity with a list of authors or an error message.
     */
    public ResponseEntity<List<Author>> getAllAuthorByBookId(Long bookId) {
        // Retrieve a list of authors associated with the given book ID
        List<Author> authors = authorRepository.findByBookId(bookId);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    /**
     * Update an author's information.
     *
     * @param authorId The ID of the author to be updated.
     * @param authorR The updated author information.
     * @return ResponseEntity with the updated author or an error message.
     */
    public ResponseEntity<Object> updateAuthor(long authorId, Author updateAuthor) {
        // Find the author by ID
        Optional<Author> author = authorRepository.findById(authorId);

        if (author.isEmpty()) {
            // Author with the given ID not found
            return new ResponseEntity<>("Author not found", HttpStatus.BAD_REQUEST);
        }

        if (!isValidAuthor(updateAuthor)) {
            // Invalid author data
            return new ResponseEntity<>("Bad Request for Author", HttpStatus.BAD_REQUEST);
        }

        // If the author exists and the author data is valid, update its information
        Author existingAuthor = author.get();
        existingAuthor.setName(updateAuthor.getName());
        return new ResponseEntity<>(authorRepository.save(existingAuthor), HttpStatus.OK);
    }

    /**
     * Delete an author.
     *
     * @param id The ID of the author to be deleted.
     * @return ResponseEntity with success status or an error message.
     */
    public ResponseEntity<HttpStatus> deleteAuthor(long id) {
        // Find the author by ID
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            // Author with the given ID not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // If the author exists, delete it
        authorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Validates author data.
     *
     * @param author The author to validate.
     * @return True if the author data is valid; otherwise, false.
     */
    private boolean isValidAuthor(Author author) {
        return author != null && !author.getName().isBlank();
    }
}
