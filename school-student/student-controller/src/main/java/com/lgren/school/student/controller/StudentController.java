package com.lgren.school.student.controller;

import com.lgren.school.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @RequestMapping("index")
    public String index() {
        System.out.println(studentService.selectAll());
        return "index";
    }

}
