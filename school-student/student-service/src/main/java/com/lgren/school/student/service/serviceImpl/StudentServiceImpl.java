package com.lgren.school.student.service.serviceImpl;

import com.lgren.school.student.dao.IStudentDao;
import com.lgren.school.student.pojo.Student;
import com.lgren.school.student.service.IStudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "studentService")
public class StudentServiceImpl implements IStudentService {
    private Logger logger = Logger.getLogger(StudentServiceImpl.class);
    @Autowired
    private IStudentDao studentDao ;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return studentDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Student record) {
        return studentDao.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentDao.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(Long id) {
        return studentDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Student> selectAll() {
        return studentDao.selectAll();
    }
}
