package com.example.reusebook.Model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SUID")
    private Long id;

    @Column(name = "name")
    private String name;

    // Default constructor
    public Student() {
    }

    // Constructor with name
    public Student(String name) {
        this.name = name;
    }

    // Getter for student ID
    public Long getId() {
        return id;
    }

    // Setter for student ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for student name
    public String getName() {
        return name;
    }

    // Setter for student name
    public void setName(String name) {
        this.name = name;
    }

    // String representation of the Student object
    @Override
    public String toString() {
        return "Student [ ID=" + id + ", Name= " + name + " ]";
    }
}
