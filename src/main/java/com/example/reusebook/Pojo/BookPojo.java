package com.example.reusebook.Pojo;

public class BookPojo {


    private Long BookPojoID;
    private String BookTitle;
    private String ISBN;

    private String BookEdition;

    private String yearOfPublication;

    private boolean isAvailable;

    private String price;

    private String StudentId;

    public Long getBookPojoID() {
        return BookPojoID;
    }

    public void setBookPojoID(Long BookPojoID) {
        this.BookPojoID = BookPojoID;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String BookTitle) {
        this.BookTitle = BookTitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookEdition() {
        return BookEdition;
    }

    public void setBookEdition(String BookEdition) {
        this.BookEdition = BookEdition;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        isAvailable = isAvailable;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
