package com.example.reusebook.Pojo;

/**
 * A Plain Old Java Object (POJO) representing a transaction.
 */
public class TransactionPojo {
    private Long id; // Transaction ID
    private String title; // Book title
    private String isbn; // ISBN (International Standard Book Number)
    private String edition; // Book edition
    private String name; // Author or student name
    private String price; // Transaction price information
    private String type; // Transaction type

    /**
     * Constructor to initialize a TransactionPojo with the provided values.
     *
     * @param id      The transaction ID.
     * @param title   The book title.
     * @param isbn    The ISBN.
     * @param edition The book edition.
     * @param name    The author or student name.
     * @param price   The transaction price information.
     * @param type    The transaction type.
     */
    public TransactionPojo(Long id, String title, String isbn, String edition, String name, String price, String type) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.edition = edition;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    /**
     * Get a string representation of the TransactionPojo.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "[ ID=" + id +
                ", Title='" + title +
                ", Isbn='" + isbn +
                ", Edition='" + edition +
                ", Name='" + name +
                ", Price='" + price  +
                ", Type='" + type +
                " ]";
    }
}
