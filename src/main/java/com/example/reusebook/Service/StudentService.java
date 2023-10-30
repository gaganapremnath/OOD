package com.example.reusebook.Service;

import com.example.reusebook.Model.Student;
import com.example.reusebook.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Add a new student.
     *
     * @param student The student entity to be added.
     * @return ResponseEntity with the added student or an error message.
     */
    public ResponseEntity<Object> addStudent(Student student) {
        if (isValidStudentName(student.getName())) {
            Student addedStudent = studentRepository.save(student);
            return new ResponseEntity<>(addedStudent, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid Student Name", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update student information by student ID.
     *
     * @param studentId  The ID of the student to be updated.
     * @param studentR   The updated student information.
     * @return ResponseEntity with the updated student or an error message.
     */
    public ResponseEntity<Object> updateStudent(long studentId, Student studentR) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Student existingStudent = student.get();
            if (isValidStudentName(studentR.getName())) {
                existingStudent.setName(studentR.getName());
                return new ResponseEntity<>(studentRepository.save(existingStudent), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid Student Name", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Student ID not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a student by student ID.
     *
     * @param id The ID of the student to be deleted.
     * @return ResponseEntity with HTTP status.
     */
    public ResponseEntity<HttpStatus> deleteStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Helper method to validate student name
    private boolean isValidStudentName(String name) {
        return name != null && !name.isEmpty() && !name.isBlank();
    }
}
