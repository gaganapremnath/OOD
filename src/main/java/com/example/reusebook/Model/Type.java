package com.example.reusebook.Model;

import javax.persistence.*;

@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    // Default constructor
    public Type() {
    }

    // Constructor with type name
    public Type(String name) {
        this.name = name;
    }

    // Getter for the type name
    public String getName() {
        return name;
    }

    // Setter for the type name
    public void setName(String name) {
        this.name = name;
    }

    // String representation of the Type object
    @Override
    public String toString() {
        return "Type [ ID= " + id +
                ", Name= " + name + " ]";
    }
}
