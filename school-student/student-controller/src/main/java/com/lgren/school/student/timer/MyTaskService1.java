package com.lgren.school.student.timer;

import org.springframework.stereotype.Service;

@Service("myTaskService1")
public class MyTaskService1 {

    public void test() {
        System.out.println(this.getClass().getName() + "\ttest");
    }

    public void test1() {
        System.out.println(this.getClass().getName() + "\ttest1");
    }

    public void test2() {
        System.out.println(this.getClass().getName() + "\ttest2");
    }
}
