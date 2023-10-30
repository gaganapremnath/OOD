package com.example.reusebook.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long PriceID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Book book;

    @Column(name = "price")
    private String price;

    // Default constructor
    public Price(){}

    // Constructor with book and price
    public Price(Book book, String price) {
        this.book = book;
        this.price = price;
    }

    // Getter for price ID
    public Long getPriceID() {
        return PriceID;
    }

    // Getter for the associated book
    public Book getBook() {
        return book;
    }
    
    // Setter for the associated book
    public void setBook(Book book) {
        this.book = book;
    }

    // Getter for book price
    public String getPrice() {
        return price;
    }

    // Setter for the associated book price
    public void setPrice(String price) {
        this.price = price;
    }

    // String representation of the Price object
    @Override
    public String toString() {
        return "Price [ Price ID=" + PriceID + ", Book=" + book + ", Price=" + price + " ]";
    }
}
