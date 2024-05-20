package com.example.schoolmanagementsystem.controllers;

import com.example.schoolmanagementsystem.entities.Student;
import com.example.schoolmanagementsystem.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    public StudentService service;

    public StudentController(StudentService service){
        super();
        this.service = service;
    }

    @GetMapping("/students")
    public String getAllStudents(ModelMap map){
        map.addAttribute("students", service.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        // create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        //service.deleteStudent(service.getStudentById(id));
        service.deleteStudentById(id);
        return "redirect:/students";
    }

    @GetMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, ModelMap model){
        model.addAttribute("student", service.getStudentById(id));
        return "update_student";
    }

    @PostMapping("/students/{id}")
    public String sendUpdateInfo(@PathVariable Long id, @ModelAttribute("student") Student student){
        // get student from database by id
        Student existingStudent = service.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());

        // save updated student object
        service.updateStudent(existingStudent);
        return "redirect:students";
    }

    @GetMapping("/students/{name}")
    public String findByName(@PathVariable String name, ModelMap model){
        model.addAttribute("students", service.findByFirstName(name));
        //model.addAttribute("students", service.findingByFirstName(name));
        return "students";
    }

    @GetMapping("/students/ordered")
    public String orderByAge(Model model){
        model.addAttribute("students", service.orderByAge());
        return "students";
    }

    @GetMapping("/students/order/{age}")
    public String orderStudentsByAge(@PathVariable int age, Model model){
        model.addAttribute("students", service.orderStudentsByAge(age));
        return "students";
    }
}
