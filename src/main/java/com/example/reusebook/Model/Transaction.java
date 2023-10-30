package com.example.reusebook.Model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Book book; // The book involved in this transaction

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Student student; // The student involved in this transaction

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "price_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Price price; // The price associated with this transaction

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Type type; // The type of this transaction

    // Default constructor
    public Transaction() {
    }

    // Constructor with transaction details
    public Transaction(Book book, Student student, Price price, Type type) {
        this.book = book;
        this.student = student;
        this.price = price;
        this.type = type;
    }

    // Getter for the transaction ID
    public Long getId() {
        return id;
    }

    // Getter for the book in the transaction
    public Book getBook() {
        return book;
    }

    // Setter for the book in the transaction
    public void setBook(Book book) {
        this.book = book;
    }

    // Getter for the student in the transaction
    public Student getStudent() {
        return student;
    }

    // Setter for the student in the transaction
    public void setStudent(Student student) {
        this.student = student;
    }

    // Getter for the price associated with the transaction
    public Price getPrice() {
        return price;
    }

    // Setter for the price associated with the transaction
    public void setPrice(Price price) {
        this.price = price;
    }

    // Getter for the type of the transaction
    public Type getType() {
        return type;
    }

    // Setter for the type of the transaction
    public void setType(Type type) {
        this.type = type;
    }

    // String representation of the Transaction object
    @Override
    public String toString() {
        return "Transaction [ ID=" + id +", Book=" + book +
                ", Student=" + student +", Price=" + price +
                ", Type=" + type + " ]";
    }
}
