package com.lgren.school.student.service;

import com.lgren.common.CResult;
import com.lgren.school.student.pojo.Student;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IStudentService {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectAll();

    CResult aopTest(Integer a, String b, String c , Date d, Long e, Map f, List g, Set h);
}