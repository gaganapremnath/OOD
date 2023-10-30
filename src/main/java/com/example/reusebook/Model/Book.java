package com.example.reusebook.Model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "Edition")
    private String edition;

    @Column(name = "YearOfPublication")
    private String yearOfPublication;

    @Column(name = "isAvailable")
    private boolean isAvailable;

    // Default constructor
    public Book() {
    }

    // Constructor with book details
    public Book(String title, String isbn, String edition, String yearOfPublication, boolean isAvailable) {
        this.title = title;
        this.isbn = isbn;
        this.edition = edition;
        this.yearOfPublication = yearOfPublication;
        this.isAvailable = isAvailable;
    }

    // Getter for book ID
    public Long getId() {
        return id;
    }

    // Getter for book title
    public String getTitle() {
        return title;
    }

    // Setter for book title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for book ISBN
    public String getIsbn() {
        return isbn;
    }

    // Setter for book ISBN
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getter for book edition
    public String getEdition() {
        return edition;
    }

    // Setter for book edition
    public void setEdition(String edition) {
        this.edition = edition;
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
        return "Book [ ID= " + id +", Title= " + title +", ISBN = " + isbn + ", Edition= " + edition + ", YearOfPublication= " + yearOfPublication +", IsAvailable= " + isAvailable +" ]";
    }
}
