package com.example.student.studentclass;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student bond = new Student(
                    "Bond",
                    "bond.1997@test.com",
                    23,
                    LocalDate.of(1997, 2, 2));

            Student james = new Student(
                    "James",
                    "james.1997@test.com",
                    24,
                    LocalDate.of(1997, 2, 2));
            repository.saveAll(List.of(bond, james));
        };
    }
}
