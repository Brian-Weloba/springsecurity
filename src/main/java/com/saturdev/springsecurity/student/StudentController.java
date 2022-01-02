package com.saturdev.springsecurity.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "James Bond"),
        new Student(2, "Maria Jones"),
        new Student(3,"Peter Parker"),
        new Student(4,"John Doe")
    );
    
    @GetMapping(path="{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
            return STUDENTS.stream()
            .filter(student -> studentId.equals(student.getStudentId()))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("Student "+ studentId+" not found"));
    }
}