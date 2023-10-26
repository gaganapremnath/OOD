package com.example.reusebook.Model;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

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

    public Book() {

    }

    public Book(String title, String isbn, String edition, String yearOfPublication ,boolean isAvailable) {
        this.title = title;
        this.isbn = isbn;
        this.edition = edition;
        this.isAvailable = isAvailable;
        this.yearOfPublication = yearOfPublication;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public boolean getisAvailable() {
        return isAvailable;
    }

    public void setisAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", edition='" + edition + '\'' +
                ", yearOfPublication='" + yearOfPublication + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}