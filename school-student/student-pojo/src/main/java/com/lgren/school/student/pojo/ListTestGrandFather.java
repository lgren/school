package com.lgren.school.student.pojo;

import java.util.List;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-08-31 19:07
 **/
public class ListTestGrandFather {
    private String name;

    private List<ListTestFather> fatherList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListTestFather> getFatherList() {
        return fatherList;
    }

    public void setFatherList(List<ListTestFather> fatherList) {
        this.fatherList = fatherList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"fatherList\":")
                .append(fatherList);
        sb.append('}');
        return sb.toString();
    }
}
