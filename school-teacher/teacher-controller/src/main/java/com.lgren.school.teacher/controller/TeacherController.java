package com.lgren.school.teacher.controller;

import com.lgren.common.CResult;
import com.lgren.school.student.service.IStudentService;
import com.lgren.school.teacher.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "selectAllTeacher.do")
    public CResult selectAllTeacher() {
        return new CResult(teacherService.selectAll());
    }
    @RequestMapping(value = "selectAllStudent.do")
    public CResult selectAllStudent() {
        return new CResult(studentService.selectAll());
    }

    @RequestMapping(value = "test")
    public String paramTest(String param1, Integer param2) {
        System.out.println(param1 + "," + param2);
        return "OK";
    }

}
