package com.lgren.school.teacher.controller;

import com.lgren.common.vo.CommonResult;
import com.lgren.school.teacher.pojo.Teacher;
import com.lgren.school.teacher.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @RequestMapping(value = "selectAllTeacher.do")
    public CommonResult selectAllTeacher() {
        return new CommonResult(teacherService.selectAll());
    }

    @RequestMapping(value = "test")
    public String paramTest(String param1, Integer param2) {
        System.out.println(param1 + "," + param2);
        return "OK";
    }

}
