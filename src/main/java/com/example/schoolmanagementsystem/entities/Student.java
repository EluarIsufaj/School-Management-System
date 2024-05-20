package com.example.schoolmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "student")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "student_age", nullable = false)
    private int age;
}
