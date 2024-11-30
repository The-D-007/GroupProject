package com.database.project.group4.controller;

import com.database.project.group4.database.GenericDatabase;
import com.database.project.group4.database.InstructorDatabase;
import com.database.project.group4.database.StudentDatabase;
import com.database.project.group4.entities.Department;
import com.database.project.group4.entities.Instructor;
import com.database.project.group4.entities.Student;
import com.database.project.group4.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CollegeController {


    private final GenericDatabase genericDatabase;
    private final InstructorDatabase instructorDatabase;


    public CollegeController(GenericDatabase genericDatabase, InstructorDatabase instructorDatabase) {
        this.genericDatabase = genericDatabase;
        this.instructorDatabase = instructorDatabase;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/faculty")
    public String getFacultyPage(Model model) {
        List<Instructor> instructors = instructorDatabase.getAll();
        model.addAttribute("instructors", instructors);
        return "faculty";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }

    @GetMapping("/registerUser")
    public String getRegisterPage() {
        return "registerUser";
    }

    @PostMapping("/registerUser")
    public String createNewStudentAcc(@ModelAttribute User user, Model model) {
        genericDatabase.createNewUser(user);
        Long userId = genericDatabase.findUserAccount(user.getEmail()).getUserid();
        genericDatabase.addRole(userId, Long.valueOf(1));

        model.addAttribute("email", user.getEmail());
        List<Department> departments = genericDatabase.getAllDepartment();
        model.addAttribute("departments", departments);
        return "registerStudent";
    }

    @GetMapping("/error")
    public String getError() {
        return "error";
    }
}