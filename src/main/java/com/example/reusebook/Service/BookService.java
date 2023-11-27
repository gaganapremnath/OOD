package com.example.reusebook.Service;

import com.example.reusebook.Constants.DiscountConstants;
import com.example.reusebook.Interface.Discount_Scheme;
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

    /**
     * Add a book to the system.
     *
     * @param bookPojo The book information to be added.
     * @return ResponseEntity with the added book or an error message.
     */
    public ResponseEntity<Book> addBook(BookPojo bookPojo) {
        // Get or create the book
        Book book = getOrCreateBook(bookPojo);
        // Calculate the price based on the discount strategy
        Price price = calculatePrice(bookPojo, book);

        // Mark the book as available
        book.setAvailable(true);
        Book savedBook = bookRepository.save(book);

        // If the price is new, save it
        if (price.getId() == null) {
            price.setBook(savedBook);
        }
        Price savedPrice = priceRepository.save(price);

        // Create a transaction and save it
        Transaction transaction = createTransaction(savedBook, bookPojo, savedPrice, DiscountConstants.BOUGHT);
        saveTransaction(transaction);

        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }

    /**
     * Mark a book as sold.
     *
     * @param bookPojo The book information to be marked as sold.
     * @return ResponseEntity with the updated book information or an error message.
     */
    public ResponseEntity<BookPojo> sellBook(BookPojo bookPojo) {
        // Get the book and mark it as unavailable
        Book book = getOrCreateBook(bookPojo);
        book.setAvailable(false);
        bookRepository.save(book);

        // Get the latest price for the book
        Price price = priceRepository.find_LatestPrice_ByBookId(book.getId());

        // Create a sell transaction and save it
        Transaction transaction = createTransaction(book, bookPojo, price, DiscountConstants.SELL);
        saveTransaction(transaction);

        // Prepare the response data
        BookPojo response = prepareResponse(book, price);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get a pageable object for pagination and sorting.
     *
     * @param pageNo   The page number.
     * @param pageSize The number of items per page.
     * @param sortBy   The field to sort by.
     * @param sortDir  The sorting direction (ASC or DESC).
     * @return A Pageable object for pagination and sorting.
     */
    public Pageable getPageable(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return PageRequest.of(pageNo, pageSize, sort);
    }

    /**
     * Get a list of all books with pagination and sorting.
     *
     * @param pageNo   The page number.
     * @param pageSize The number of items per page.
     * @param sortBy   The field to sort by.
     * @param sortDir  The sorting direction (ASC or DESC).
     * @return ResponseEntity with the list of books and HTTP status.
     */
    public ResponseEntity<List<Book>> getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = getPageable(pageNo, pageSize, sortBy, sortDir);
        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<Book> listOfBooks = bookPage.getContent();

        return new ResponseEntity<>(listOfBooks, HttpStatus.OK);
    }

    /**
     * Search for books based on a search key with pagination and sorting.
     *
     * @param searchKey The search key to filter books.
     * @param pageNo     The page number.
     * @param pageSize   The number of items per page.
     * @param sortBy     The field to sort by.
     * @param sortDir    The sorting direction (ASC or DESC).
     * @return ResponseEntity with the list of matching books or an error message.
     */
    public ResponseEntity<Object> getBooks(String searchKey, int pageNo, int pageSize, String sortBy, String sortDir) {
        if (!searchKey.isBlank()) {
            Pageable pageable = getPageable(pageNo, pageSize, sortBy, sortDir);
            searchKey = searchKey.trim().toLowerCase();
            List<Book> books = bookRepository.findBy_Title_Edition_Isbn_IsAvailable(searchKey, pageable);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            // Return a bad request response if the search key is empty
            return new ResponseEntity<>("Please enter some search key", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update book information.
     *
     * @param bookPojo The book information to be updated.
     * @return ResponseEntity with the updated book or an error message.
     */
    public ResponseEntity<Book> updateBook(BookPojo bookPojo) {
        // Get or create the book from the provided data
        Book book = getOrCreateBook(bookPojo);
        // Update book information
        book.setIsbn(bookPojo.getIsbn());
        book.setEdition(bookPojo.getEdition());
        book.setTitle(bookPojo.getTitle());
        // Return the updated book and HTTP status
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
    }

    /**
     * Delete a book by its ID.
     *
     * @param id The ID of the book to be deleted.
     * @return ResponseEntity with HTTP status.
     */
    public ResponseEntity<HttpStatus> deleteBook(long id) {
        // Delete the book with the provided ID
        bookRepository.deleteById(id);
        // Return a no content response upon successful deletion
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Private helper methods:
    private Book getOrCreateBook(BookPojo bookPojo) {
        if (bookPojo.getId() != null) {
            return bookRepository.findById(bookPojo.getId()).orElseThrow();
        } else {
            return new Book(bookPojo.getTitle(), bookPojo.getIsbn(), bookPojo.getEdition(), bookPojo.getYearOfPublication(), true);
        }
    }

    private Price calculatePrice(BookPojo bookPojo, Book book) {
        if (bookPojo.getId() != null) {
            Price price = priceRepository.find_LatestPrice_ByBookId(bookPojo.getId());
            return new Price(book, calculateDiscount(price, book));
        } else {
            return new Price(book, bookPojo.getPrice());
        }
    }

    private String calculateDiscount(Price price, Book book) {
        Discount_Scheme discount = new PublicationAgeDiscount();

        return discount.getDiscountDetails(Integer.parseInt(price.getPrice()));
    }

    private Transaction createTransaction(Book book, BookPojo bookPojo, Price price, DiscountConstants sell) {
        return new Transaction(book, getStudent(bookPojo.getStudentId()), price, getType(sell));
    }

    private void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    private Student getStudent(String studentId) {
        return studentRepository.findById(Long.parseLong(studentId)).orElseThrow();
    }

    private Type getType(DiscountConstants sell) {
        Long typeId = Long.parseLong(sell.getValue());
        return typeRepository.findById(typeId).orElseThrow();
    }

    private BookPojo prepareResponse(Book book, Price price) {
        BookPojo response = new BookPojo();
        response.setAvailable(false);
        response.setEdition(book.getEdition());
        response.setIsbn(book.getIsbn());
        response.setPrice(price.getPrice());
        response.setTitle(book.getTitle());
        return response;
    }
}
