package com.lgren.school.student.service.serviceImpl;

import com.lgren.common.vo.CommonResult;
import com.lgren.school.student.dao.IStudentDao;
import com.lgren.school.student.pojo.Student;
import com.lgren.school.student.service.IStudentService;
import com.lgren.school.student.service.aop.NotBlankTest;
import com.lgren.school.student.service.aop.NotEmptyTest;
import com.lgren.school.student.service.aop.NotNullTest;
import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public CommonResult aopTest(Integer a, String b, @NotBlankTest(feedbackType = 4) String c, Date d, Long e, @NotEmptyTest Map f, List g, @NotNullTest Set h) {
        List<Object> list = new LinkedList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);
        list.add(h);
        return new CommonResult(list);
    }
}
