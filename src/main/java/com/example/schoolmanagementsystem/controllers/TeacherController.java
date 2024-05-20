package com.example.schoolmanagementsystem.controllers;

import com.example.schoolmanagementsystem.services.TeacherService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    public TeacherService service;

    public TeacherController(TeacherService service){
        this.service = service;
    }

    @GetMapping("/teachers")
    public String getAllTeachers(Model model){
        model.addAttribute("teachers" , service.getAllTeachers());
        return "teachers";
    }
}
