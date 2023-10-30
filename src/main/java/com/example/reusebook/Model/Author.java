package com.example.reusebook.Model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Book book;  // The book associated with this author

    // Getter for author's unique identifier
    public Long getId() {
        return id;
    }

    // Getter for author's name
    public String getName() {
        return name;
    }

    // Setter for author's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the associated book
    public Book getBook() {
        return this.book;
    }

    // Setter for the associated book
    public void setBook(Book book) {
        this.book = book;
    }

    // String representation of the Author object
    @Override
    public String toString() {
        return "Author [ Name=" + name + " ]";
    }
}
