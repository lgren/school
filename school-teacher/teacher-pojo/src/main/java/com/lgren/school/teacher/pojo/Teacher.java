package com.lgren.school.teacher.pojo;

import java.util.Date;

public class Teacher {
    private Long id;

    private String username;

    private String password;

    private String realName;

    private String subject;

    private Date birthday;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", subject='" + subject + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public Teacher() {
    }

    public Teacher(Long id, String username, String password, String realName, String subject, Date birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.subject = subject;
        this.birthday = birthday;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}