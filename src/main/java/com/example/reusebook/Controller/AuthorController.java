package com.example.reusebook.Controller;


import com.example.reusebook.Model.Author;
import com.example.reusebook.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/authors")

public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @PostMapping(value = "/{bookId}/addAuthor")
    public ResponseEntity<Object> addAuthor(@PathVariable(value = "bookId") Long bookId, @RequestBody Author author){
        return authorService.addAuthor(bookId, author);
    }

    @GetMapping("/{bookId}/getAuthors")
    public ResponseEntity<List<Author>> getAllAuthorByBookId(@PathVariable(value = "bookId") Long bookId) {
        return authorService.getAllAuthorByBookId(bookId);
    }

    @PutMapping("/editAuthor/{authorId}")
    public ResponseEntity<Object> updateAuthor(@PathVariable("authorId") long authorId, @RequestBody Author authorR) {
        return authorService.updateAuthor(authorId,authorR);
    }

    @DeleteMapping("/deleteAuthor/{authorId}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("authorId") long authorId) {
        return authorService.deleteAuthor(authorId);
    }


}
