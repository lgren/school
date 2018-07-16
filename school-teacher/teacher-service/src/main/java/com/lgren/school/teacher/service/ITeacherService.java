package com.lgren.school.teacher.service;

import com.lgren.school.teacher.pojo.Teacher;

import java.util.List;

public interface ITeacherService {
    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> selectAll();
}