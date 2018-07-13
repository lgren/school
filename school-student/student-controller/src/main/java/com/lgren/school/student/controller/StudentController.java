package com.lgren.school.student.controller;

import com.lgren.common.vo.CommonResult;
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
        System.out.println(studentService.selectByPrimaryKey(1L));
        return "index";
    }

    @RequestMapping("selectAllStudent.do")
    public CommonResult selectAllStudent() {
        return new CommonResult(studentService.selectAll());
    }

}
