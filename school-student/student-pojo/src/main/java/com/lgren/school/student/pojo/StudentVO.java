package com.lgren.school.student.pojo;

import java.io.Serializable;

public class StudentVO extends Student implements Serializable {
    private String realName;

    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String getRealName() {
        return realName;
    }

    @Override
    public void setRealName(String realName) {
        this.realName = realName;
    }
}