package com.lgren.school.student.pojo;

import java.util.List;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-08-31 19:07
 **/
public class ListTestFather {
    private String name;

    private List<ListTest> list;

    private ListTestGrandFather grandFather;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListTest> getList() {
        return list;
    }

    public void setList(List<ListTest> list) {
        this.list = list;
    }

    public ListTestGrandFather getGrandFather() {
        return grandFather;
    }

    public void setGrandFather(ListTestGrandFather grandFather) {
        this.grandFather = grandFather;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"list\":")
                .append(list);
        sb.append(",\"grandFather\":")
                .append(grandFather);
        sb.append('}');
        return sb.toString();
    }
}
