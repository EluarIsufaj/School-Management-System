package com.example.schoolmanagementsystem.repositories;

import com.example.schoolmanagementsystem.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findByEmailAndPassword(String email, String password);

    List<Teacher> findAllByFirstName(String firstName);

    Teacher findByFirstNameAndLastName(String firstName, String lastName);
}
