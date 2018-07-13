package com.lgren.school.student.dao;

import com.lgren.school.student.pojo.Student;

import java.util.List;

public interface IStudentDao {
    List<Student> selectAll();
    Student selectById(Long id);
}
