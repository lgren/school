package com.lgren.school.student.serviceImpl;

import com.lgren.school.student.dao.IStudentDao;
import com.lgren.school.student.pojo.Student;
import com.lgren.school.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentDao studentDao ;

    @Override
    public List<Student> selectAll() {
        return studentDao.selectAll();
    }

    @Override
    public Student selectById(Long id) {
        return studentDao.selectById(id);
    }
}
