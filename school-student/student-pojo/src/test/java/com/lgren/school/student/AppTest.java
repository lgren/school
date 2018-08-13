package com.lgren.school.student;

import static org.junit.Assert.assertTrue;

import com.lgren.school.student.pojo.Student;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        Student student = new Student(1L,"test","test","test", new Date());
        System.out.println(student.getBirthday());
    }
}
