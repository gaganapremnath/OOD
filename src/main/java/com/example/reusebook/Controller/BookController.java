package com.example.reusebook.Controller;

import com.example.reusebook.Constants.AppConstants;
import com.example.reusebook.Model.Book;
import com.example.reusebook.Pojo.BookPojo;
import com.example.reusebook.Repository.TransactionRepository;
import com.example.reusebook.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path="/books") // This means URL's start with /demo (after Application path)
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping(value = "/")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Hello World",HttpStatus.OK);
    }

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
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return bookService.getAllBooks(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping(value = "/getBooksOn")
    public ResponseEntity<Object> getBooks(
            @RequestParam(value = "searchKey", required = false) String searchKey,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return bookService.getBooks(searchKey,pageNo,pageSize,sortBy,sortDir);
    }

    @PutMapping("/editBook")
    public ResponseEntity<Book> updateBook(@RequestBody BookPojo bookPojo) {
        return bookService.updateBook(bookPojo);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("bookId") long id) {
        return bookService.deleteBook(id);
    }

}
