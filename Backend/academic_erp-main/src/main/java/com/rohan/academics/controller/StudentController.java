package com.rohan.academics.controller;

import com.rohan.academics.entity.Student;
import com.rohan.academics.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getstudentsbydomain/{domainId}")
    public ResponseEntity<List<Student>> getStudentsByDomain(@PathVariable int domainId) {
        try {
            List<Student> students = studentService.getStudentsByDomain(domainId);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

