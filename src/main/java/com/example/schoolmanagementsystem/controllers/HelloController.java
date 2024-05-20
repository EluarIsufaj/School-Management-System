package com.example.schoolmanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping( "/hi")
    public String goToWelcomePage(final ModelMap modelMap) {
        modelMap.addAttribute("sayHello", "Ckemi, kjo eshste faqja e pare");
        return "index";
    }

}
