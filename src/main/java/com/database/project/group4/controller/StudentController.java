package com.database.project.group4.controller;

import com.database.project.group4.database.GenericDatabase;
import com.database.project.group4.database.StudentDatabase;
import com.database.project.group4.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    private final GenericDatabase genericDatabase;
    private Student student;

    private final StudentDatabase studentDatabase;

    public StudentController(StudentDatabase studentDatabase, GenericDatabase genericDatabase) {
        this.studentDatabase = studentDatabase;
        this.genericDatabase = genericDatabase;
    }

    @GetMapping("/")
    public String insertStudent(){
//        student = new Student(1,"John Doe", "jo@example.com", "123-788-7890", LocalDate.of(2024, 5, 10), 3.75, 1);
//        studentDatabase.insertStudent(student);
        return "index";
    }

    @GetMapping("/course")
    public String getCoursePage(Authentication auth, Model model){
        String email = auth.getName();
        student = studentDatabase.getStudent(email);
            System.out.println("Student ID: " + student.getStudentID() + " and student name: " + student.getStudentName());

        List<String> roleList = new ArrayList<>();
        for (GrantedAuthority ga : auth.getAuthorities())
        {
            roleList.add(ga.getAuthority());
        }
        model.addAttribute("username", email);
        model.addAttribute("roles", roleList);
        return "course";
    }
}
