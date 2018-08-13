package com.lgren.school.student.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Student implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String realName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public Student() {
    }

    public Student(Long id, String username, String password, String realName, Date birthday) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthdayStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return birthday != null? sdf.format(birthday) : null;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}