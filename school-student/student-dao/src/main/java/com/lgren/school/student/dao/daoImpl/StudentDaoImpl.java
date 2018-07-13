package com.lgren.school.student.dao.daoImpl;

import com.lgren.school.student.dao.IStudentDao;
import com.lgren.school.student.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class StudentDaoImpl implements IStudentDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Student record) {
        return 0;
    }

    @Override
    public int insertSelective(Student record) {
        return 0;
    }

    @Override
    public Student selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return 0;
    }

    @Override
    public List<Student> selectAll() {
        return null;
    }
}
