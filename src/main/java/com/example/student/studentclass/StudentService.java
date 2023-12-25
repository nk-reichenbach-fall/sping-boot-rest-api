package com.example.student.studentclass;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }

        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student id:" + studentId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> emailId = studentRepository.findByEmail(email);
            if (emailId.isPresent()) {
                throw new IllegalStateException("Student email: " + email + " already exists");
            }

            student.setEmail(email);
        }
    }

    public void deleteStudent(Long studentId) {

        // Another way of checking for student apart from findById().orElseThrow()
        boolean student = studentRepository.existsById(studentId);
        if (!student) {
            throw new IllegalStateException("Student id:" + studentId + " does not exist");
        }

        studentRepository.deleteById(studentId);
    }
}
