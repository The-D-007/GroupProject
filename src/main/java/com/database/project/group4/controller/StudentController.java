package com.database.project.group4.controller;

import com.database.project.group4.database.GenericDatabase;
import com.database.project.group4.database.StudentDatabase;
import com.database.project.group4.entities.Department;
import com.database.project.group4.entities.Student;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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
    public String insertStudent() {
        return "index";
    }

    @GetMapping("/course")
    public String getCoursePage(Model model) {
        List<Department> departments = genericDatabase.findAll();
        model.addAttribute("departments", departments);
        return "course";
    }

    @GetMapping("/profile")
    public String getProfile(Authentication auth, Model model) {
        String email = auth.getName();
        student = studentDatabase.getStudentAll(email);
        model.addAttribute("Student", student);
        return "profile";
    }

    @PostMapping("/addCourses")
    public String setCourses(@RequestParam List<Integer> courseIds, Authentication auth) {
        student = studentDatabase.getStudent(auth.getName());
        studentDatabase.addCourses(courseIds, student.getStudentID());
        return "redirect:/";
    }

    @PostMapping("/dropCourse")
    public String dropCourse(@RequestParam int courseId, Authentication auth) {
        student = studentDatabase.getStudent(auth.getName());
        studentDatabase.dropCourse(courseId, student.getStudentID());
        return "redirect:/";
    }

    @PostMapping("/registerStudent")
    public String createNewStudentAcc(@RequestParam String name, @RequestParam String email, @RequestParam String phone_number, @RequestParam LocalDate date_of_birth, @RequestParam int departmentId, @RequestParam double gpa) {
        Student student1 = new Student();
        student1.setStudentName(name);
        student1.setStudentEmail(email);
        student1.setStudentDOB(date_of_birth);
        student1.setStudentDepartmentId(departmentId);
        student1.setStudentPhoneNumber(phone_number);
        student1.setStudentGPA(gpa);
        studentDatabase.createNewStudent(student1);
        return "redirect:/";
    }


}
