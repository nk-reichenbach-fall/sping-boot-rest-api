package com.example.student.studentclass;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @GetMapping()
    public Student getStudents() {
        return new Student("Naresh", "Naresh.test@gmnail.com", 26, LocalDate.now());
    }

}
