package com.lgren.school.student.daoImpl;

import com.lgren.school.student.dao.IStudentDao;
import com.lgren.school.student.pojo.Student;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class StudentDaoImpl implements IStudentDao {
    @Override
    public List<Student> selectAll() {
        List<Student> studentList = new ArrayList<>();
        Student studentOne = new Student(4243L,"studentOne","studentOne","学生1", DateUtils.addDays(new Date(),-23));
        Student studentTwo = new Student(4243L,"studentTwo","studentTwo","学生2",DateUtils.addDays(new Date(),-8));
        Student studentTree = new Student(4243L,"studentTree","studentTree","学生3",DateUtils.addDays(new Date(),-123));
        studentList.add(studentOne);
        studentList.add(studentTwo);
        studentList.add(studentTree);
        return studentList;
    }

    @Override
    public Student selectById(Long id) {
        Student studentOne = new Student(4243L,"studentOne","studentOne","学生1", DateUtils.addDays(new Date(),-23));
        return studentOne;
    }
}
