package com.example.reusebook.Service;

import com.example.reusebook.Constants.AppConstants;
import com.example.reusebook.Interface.DiscountStrategy;
import com.example.reusebook.Model.*;
import com.example.reusebook.Pojo.BookPojo;
import com.example.reusebook.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TypeRepository typeRepository;

    private Book getBook(BookPojo bookPojo){
        if(bookPojo.getId() != null){
            return bookRepository.findById(bookPojo.getId()).orElseThrow();
        }else{
            return new Book(bookPojo.getTitle(), bookPojo.getIsbn(), bookPojo.getEdition(), bookPojo.getYearOfPublication(), true);
        }
    }



    private String calculateDiscount(Price p,Book book){
        int sellTransaction = transactionRepository.getSellCount(book.getId());
        DiscountStrategy discountStrategy;
        System.out.println("Hello in " +(Integer.parseInt(book.getYearOfPublication()) - Year.now().getValue()) + " and transaction is : "+ sellTransaction);

        if(Year.now().getValue() - Integer.parseInt(book.getYearOfPublication()) <= 5 ){
            discountStrategy = new NewPublicationBook();
            return discountStrategy.getDiscountPrint(Integer.parseInt(p.getPrice()),sellTransaction);
        }
        discountStrategy = new OldPublicationBook();
        return discountStrategy.getDiscountPrint(Integer.parseInt(p.getPrice()),sellTransaction);
    }

    private Price getPrice(BookPojo bookPojo,Book book){
        if(bookPojo.getId() != null){
            Price p = priceRepository.findByIdOrderByIdDesc(bookPojo.getId());
            return new Price(book,calculateDiscount(p,book));
        }else{
            return new Price(book, bookPojo.getPrice());
        }
    }

    private Student getStudent(String studentId){
        return studentRepository.findById(new Long(studentId)).orElseThrow();
    }

    private TransactionType getType(String typeId){
        return typeRepository.findById(new Long(typeId)).orElseThrow();
    }

    private Transaction getTransaction(Book b,BookPojo bookPojo,Price p,String type){
        return new Transaction(b,getStudent(bookPojo.getStudentId()),p,getType(type));
    }

    private Price savePrice(Price p){
        return priceRepository.save(p);
    }

    private Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public ResponseEntity<Book> addBook(BookPojo bookPojo){
        Book b = getBook(bookPojo);
        Price p = null;
        boolean newBook = true;
        if(bookPojo.getId() != null){
            newBook = false;
            p = getPrice(bookPojo,b);
        }
        b.setisAvailable(true);
        System.out.println(b);
        Book bInserted = bookRepository.save(b);
        if(newBook){
            p = getPrice(bookPojo,bInserted);
        }
        Price priceInserted = savePrice(p);
        Transaction t =getTransaction(b,bookPojo,priceInserted,AppConstants.BOUGHT);
        saveTransaction(t);
        return  new ResponseEntity<>(bInserted, HttpStatus.OK);
    }

    public ResponseEntity<BookPojo> sellBook(BookPojo bookPojo){
        Book b = getBook(bookPojo);
        b.setisAvailable(false);
        bookRepository.save(b);
        Price p = priceRepository.findByIdOrderByIdDesc(b.getId());
        Transaction t = getTransaction(b,bookPojo,p,AppConstants.SELL);
        saveTransaction(t);
        BookPojo response = new BookPojo();
        response.setAvailable(false);
        response.setEdition(b.getEdition());
        response.setIsbn(b.getIsbn());
        response.setPrice(p.getPrice());
        response.setTitle(b.getTitle());
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    public Sort getSort(String sortDir,String sortBy){
        return sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
    }

    public Pageable getPageable(int pageNo,int pageSize,String sortBy,String sortDir){
        Sort sort = getSort(sortDir, sortBy);
        return PageRequest.of(pageNo, pageSize, sort);
    }

    public ResponseEntity<List<Book>> getAllBooks(int pageNo,int pageSize,String sortBy,String sortDir){
        Pageable p = getPageable(pageNo, pageSize, sortBy, sortDir);
        Page<Book> books = bookRepository.findAll(p);
        List<Book> listOfBooks = books.getContent();
        return new ResponseEntity<>(listOfBooks, HttpStatus.OK);
    }

    public ResponseEntity<Object> getBooks(String searchKey,int pageNo,int pageSize,String sortBy,String sortDir){
        if(!searchKey.isBlank()){
            Pageable p = getPageable(pageNo, pageSize, sortBy, sortDir);
            searchKey = searchKey.trim();
            List<Book> books = bookRepository.findBy_Title_Edition_Isbn_IsAvailable(searchKey.toLowerCase(),p);
            return new ResponseEntity<>(books, HttpStatus.OK);

        }
        return new ResponseEntity<>("Please enter some search key",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Book> updateBook(BookPojo bookPojo) {
        Book book = getBook(bookPojo);
        book.setIsbn(bookPojo.getIsbn());
        book.setEdition(bookPojo.getEdition());
        book.setTitle(bookPojo.getTitle());
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteBook(long id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
