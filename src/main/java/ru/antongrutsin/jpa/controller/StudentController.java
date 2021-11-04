package ru.antongrutsin.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.antongrutsin.jpa.model.Student;
import ru.antongrutsin.jpa.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String showAll (Model model){
        List<Student> students = studentService.findAllStudent();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping(value = "/update/{id}")
    public String updateStudent(Model model, @PathVariable(value = "id") int id){
        Student student  = studentService.findById(id);
        model.addAttribute("student", student);
        return "form";
    }

    @PostMapping(value = "/form")
    public String updateStudent(Student student){
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping(value = "/create")
    public String createStudent(Model model){
        Student student  = new Student();
        model.addAttribute("student", student);
        return "form";
    }

    @PostMapping(value = "/form")
    public String createStudent(Student student){
        studentService.createStudent(student);
        return "redirect:/students";
    }

}
