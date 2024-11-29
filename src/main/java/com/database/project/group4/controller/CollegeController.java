package com.database.project.group4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CollegeController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
