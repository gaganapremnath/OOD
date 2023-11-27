package com.example.reusebook.Controller;

import com.example.reusebook.Constants.PageConstants;
import com.example.reusebook.Model.Book;
import com.example.reusebook.Pojo.BookPojo;
import com.example.reusebook.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBooks")
    public  ResponseEntity<Book> addBook(@RequestBody BookPojo bookPojo){
        return bookService.addBook(bookPojo);
    }

    @PostMapping("/sellBook")
    public  ResponseEntity<BookPojo> sellBook(@RequestBody BookPojo bookPojo){
        return bookService.sellBook(bookPojo);
    }

    @GetMapping(value = "/getBooks")
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(value = "pageNo", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return bookService.getAllBooks(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping(value = "/getBooksOn")
    public ResponseEntity<Object> getBooks(
            @RequestParam(value = "searchKey", required = false) String searchKey,
            @RequestParam(value = "pageNo", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return bookService.getBooks(searchKey,pageNo,pageSize,sortBy,sortDir);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<Book> updateBook(@RequestBody BookPojo bookPojo) {
        return bookService.updateBook(bookPojo);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("bookId") long id) {
        return bookService.deleteBook(id);
    }

}
