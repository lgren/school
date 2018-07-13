package com.lgren.school.student.service;

import com.lgren.school.student.pojo.Student;

import java.util.List;

public interface IStudentService {
    List<Student> selectAll();
    Student selectById(Long id);
}
