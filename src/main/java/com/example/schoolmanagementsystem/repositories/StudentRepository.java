package com.example.schoolmanagementsystem.repositories;

import com.example.schoolmanagementsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByEmailAndPassword(String email, String password);

    Optional<Student> findByEmail(String email);

    List<Student> findAllByAgeOrderByAge(int age);

    List<Student> findAllByFirstName(String firstName);
}
