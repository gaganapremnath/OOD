package com.example.reusebook.Model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long BookID;

    @Column(name = "title")
    private String BookTitle;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "Edition")
    private String BookEdition;

    @Column(name = "YearOfPublication")
    private String yearOfPublication;

    @Column(name = "isAvailable")
    private boolean isAvailable;

    // Default constructor
    public Book() {
    }

    // Constructor with book details
    public Book(String BookTitle, String ISBN, String BookEdition, String yearOfPublication, boolean isAvailable) {
        this.BookTitle = BookTitle;
        this.ISBN = ISBN;
        this.BookEdition = BookEdition;
        this.yearOfPublication = yearOfPublication;
        this.isAvailable = isAvailable;
    }

    // Getter for book ID
    public Long getBookID() {
        return BookID;
    }

    // Getter for book title
    public String getBookTitle() {
        return BookTitle;
    }

    // Setter for book title
    public void setBookTitle(String BookTitle) {
        this.BookTitle = BookTitle;
    }

    // Getter for book ISBN
    public String getISBN() {
        return ISBN;
    }

    // Setter for book ISBN
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    // Getter for book edition
    public String getBookEdition() {
        return BookEdition;
    }

    // Setter for book edition
    public void setBookEdition(String BookEdition) {
        this.BookEdition = BookEdition;
    }

    // Getter for the year of publication
    public String getYearOfPublication() {
        return yearOfPublication;
    }

    // Setter for the year of publication
    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    // Getter for book availability
    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter for book availability
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // String representation of the Book object
    @Override
    public String toString() {
        return "Book [ Book ID= " + BookID +", Book Title= " + BookTitle +", ISBN = " + ISBN + ", Book Edition= " + BookEdition + ", YearOfPublication= " + yearOfPublication +", IsAvailable= " + isAvailable +" ]";
    }
}
