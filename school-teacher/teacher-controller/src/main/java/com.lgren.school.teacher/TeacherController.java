package com.lgren.school.teacher;

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
    public List<Teacher> selectAllTeacher() {
        return teacherService.selectAll();
    }
}
