package com.lgren.school.student.controller;

import com.lgren.school.student.pojo.Student;
import com.lgren.school.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @RequestMapping("index")
    public String index() {
        System.out.println(studentService.selectByPrimaryKey(1L));
        return "index";
    }

    @RequestMapping("selectAllStudent.do")
    public List<Student> selectAllStudent() {
        return studentService.selectAll();
    }

}
