package com.saturdev.springsecurity.student;

import java.util.*;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("management/api/v1/students")
public class StundentManagementController {
    
    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "James Bond"),
        new Student(2, "Maria Jones"),
        new Student(3,"Peter Parker"),
        new Student(4,"John Doe")
    );

    @GetMapping
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        System.out.println("Added Student: " + student);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student) {
        System.out.println("Updated Student: " + student);

    }


    @DeleteMapping(path = "{studentId}")
    
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println("Deleted Student: " + studentId);
    }


    
}
