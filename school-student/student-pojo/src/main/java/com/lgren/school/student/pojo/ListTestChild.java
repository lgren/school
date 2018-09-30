package com.lgren.school.student.pojo;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-08-31 19:07
 **/
public class ListTestChild {
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
