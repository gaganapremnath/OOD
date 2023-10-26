package com.example.reusebook.Service;

import com.example.reusebook.Model.Author;
import com.example.reusebook.Model.Book;
import com.example.reusebook.Repository.AuthorRepository;
import com.example.reusebook.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public ResponseEntity<Object> addAuthor(Long bookId, @RequestBody Author author){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            Book b = book.get();
            if(author.getName() != null && !author.getName().isBlank() && !author.getName().isEmpty()){
                author.setBook(b);
                return new ResponseEntity<>(authorRepository.save(author),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("Bad Request for Author", HttpStatus.BAD_REQUEST);
            }

        }else{
            return new ResponseEntity<>("Book Id not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Author>> getAllAuthorByBookId(Long bookId) {
        List<Author> authors = authorRepository.findByBookId(bookId);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    public ResponseEntity<Object> updateAuthor(long authorId,Author authorR) {
        Optional<Author> author = authorRepository.findById(authorId);
        if(author.isPresent()){
            Author a = author.get();
            if(authorR.getName() != null && !authorR.getName().isBlank() && !authorR.getName().isEmpty()) {
                a.setName(authorR.getName());
                return new ResponseEntity<>(authorRepository.save(a), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Bad Request for Author", HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>("Bad Request for Author", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpStatus> deleteAuthor(long id) {
        Optional<Author> author = authorRepository.findById(id);
        if(!author.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        authorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
