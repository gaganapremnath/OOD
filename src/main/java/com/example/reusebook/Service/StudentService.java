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

    public ResponseEntity<Object> addStudent(Student student){
        if(student.getName() != null && !student.getName().isEmpty() && !student.getName().isBlank()){
            Student s = studentRepository.save(student);
            return  new ResponseEntity<>(s, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Bad Request for Student", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateStudent(long studentId, Student studentR) {
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()){
            Student s = student.get();
            if(studentR.getName() != null && !studentR.getName().isEmpty() && !studentR.getName().isBlank()) {
                s.setName(studentR.getName());
                return new ResponseEntity<>(studentRepository.save(s), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Bad Request for Student", HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>("Student id not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
