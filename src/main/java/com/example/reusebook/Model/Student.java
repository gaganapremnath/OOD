package com.example.reusebook.Model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SUID")
    private Long StudentID;

    @Column(name = "name")
    private String StudentName;

    // Default constructor
    public Student() {
    }

    // Constructor with name
    public Student(String StudentName) {
        this.StudentName = StudentName;
    }

    // Getter for student ID
    public Long getStudentID() {
        return StudentID;
    }

    // Setter for student ID
    public void setStudentID(Long StudentID) {
        this.StudentID = StudentID;
    }

    // Getter for student name
    public String getStudentName() {
        return StudentName;
    }

    // Setter for student name
    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    // String representation of the Student object
    @Override
    public String toString() {
        return "Student [ Student ID=" + StudentID + ", Student Name= " + StudentName + " ]";
    }
}
