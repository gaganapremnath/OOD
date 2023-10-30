package com.example.reusebook.Pojo;

/**
 * A Plain Old Java Object (POJO) representing book data.
 */
public class BookPojo {
    private Long id; // Book ID
    private String title; // Book title
    private String isbn; // ISBN (International Standard Book Number)
    private String edition; // Book edition
    private String yearOfPublication; // Year of publication
    private boolean isAvailable; // Availability status
    private String price; // Price information
    private String studentId; // Student ID associated with the book

    // Getter and Setter methods for each property

    /**
     * Get the Book ID.
     *
     * @return The Book ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the Book ID.
     *
     * @param id The Book ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the Book title.
     *
     * @return The Book title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the Book title.
     *
     * @param title The Book title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the ISBN (International Standard Book Number).
     *
     * @return The ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set the ISBN.
     *
     * @param isbn The ISBN to set.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Get the Book edition.
     *
     * @return The Book edition.
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Set the Book edition.
     *
     * @param edition The Book edition to set.
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * Check if the book is available.
     *
     * @return True if the book is available; otherwise, false.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Set the availability status of the book.
     *
     * @param available True if the book is available; otherwise, false.
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Get the price information.
     *
     * @return The price information.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Set the price information.
     *
     * @param price The price information to set.
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Get the Student ID associated with the book.
     *
     * @return The Student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Set the Student ID associated with the book.
     *
     * @param studentId The Student ID to set.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Get the year of publication of the book.
     *
     * @return The year of publication.
     */
    public String getYearOfPublication() {
        return yearOfPublication;
    }

    /**
     * Set the year of publication of the book.
     *
     * @param yearOfPublication The year of publication to set.
     */
    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
