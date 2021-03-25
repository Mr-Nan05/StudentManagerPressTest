package com.nan.manager.controller;

import javax.validation.Valid;


import com.nan.manager.model.Student;
import com.nan.manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller // This means that this class is a Controller
@RequestMapping(path="/manager") // This means URL's start with /demo (after Application path)
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


//    @InitBinder
//    public void setAllowedFields(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
//    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewStudent (@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "failed to add student";
        } else {
            studentService.saveStudent(student);
            return "redirect:/students/" + student.getId();
        }
    }

    @PostMapping(path="/update")
    public @ResponseBody String alterStudent(@Valid Student student, BindingResult result){
        if (result.hasErrors()) {
            return "failed to update student " + student.getId();
        } else {
            studentService.updateStudent(student);
            return "update:/students/" + student.getId();
        }
    }

    @GetMapping(path="/select_by_id")
    public  @ResponseBody Iterable<Student> selectStudentById(@RequestParam Integer id){
        return studentService.findStudentById(id);
    }

    @GetMapping(path="/select_by_name")
    public  @ResponseBody Iterable<Student> selectStudentByName(@RequestParam String name){
        return studentService.findStudentByName(name);
    }

    @GetMapping(path="/delete")
    public @ResponseBody String deleteStudent(@RequestParam Integer id){
        if(studentService.deleteStudent(id))
            return "delete student " + id + " successfully!";
        return "not exist student " + id;
    }

    @GetMapping(path="/students")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return studentService.findAllStudents();
    }
}
