package com.example.reusebook.Pojo;

public class TransactionPojo {
    private Long id;
    private String title;
    private String isbn;
    private String edition;
    private String name;
    private String price;
    private String type;

    public TransactionPojo(Long id, String title, String isbn, String edition, String name, String price, String type) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.edition = edition;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", edition='" + edition + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
