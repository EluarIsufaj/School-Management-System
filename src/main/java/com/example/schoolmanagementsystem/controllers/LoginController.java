package com.example.schoolmanagementsystem.controllers;

import com.example.schoolmanagementsystem.entities.Admin;
import com.example.schoolmanagementsystem.entities.Student;
import com.example.schoolmanagementsystem.entities.User;
import com.example.schoolmanagementsystem.services.StudentService;
import com.example.schoolmanagementsystem.services.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    StudentService studentService;

    TeacherService teacherService;

    public LoginController(StudentService studentService,TeacherService teacherService){
        this.studentService = studentService;
        this.teacherService = teacherService;
    }
    @GetMapping("/login")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model){
        Student student = studentService.findByEmail(user.getEmail());
        //Teacher teacher = teacherService.findByEmail(email);
        Admin admin = new Admin(0L, "admin", "admin123");
        if (user.getEmail().equals(student.getEmail())
                && user.getPassword().equals(student.getPassword())){
            model.addAttribute("students",
                    studentService.loginStudent(user.getEmail(), student.getPassword()));
            return "students";
        }if (user.getEmail().equals(admin.getEmail())
                && user.getPassword().equals(admin.getPassword())){
            model.addAttribute("students", studentService.getAllStudents());
            return "students";
        }
        model.addAttribute("sayHello", "Incorrect");
        return "index";
    }
}
