package com.example.reusebook.Controller;


import com.example.reusebook.Model.Student;
import com.example.reusebook.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/students") // This means URL's start with /demo (after Application path)

public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public  ResponseEntity<Object> addStudent(@RequestBody Student student){
        System.out.println(student);
        return studentService.addStudent(student);
    }

    @PutMapping("/editStudent/{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable("studentId") long studentId, @RequestBody Student studentR) {
        return studentService.updateStudent(studentId,studentR);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("studentId") long id) {
        return studentService.deleteStudent(id);
    }
}
