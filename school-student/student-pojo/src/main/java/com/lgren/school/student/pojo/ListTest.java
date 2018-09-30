package com.lgren.school.student.pojo;

import java.util.List;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-08-31 19:07
 **/
public class ListTest {
    private String name;

    private List<ListTestChild> childList;

    private ListTest grandFather;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListTestChild> getChildList() {
        return childList;
    }

    public void setChildList(List<ListTestChild> childList) {
        this.childList = childList;
    }

    public ListTest getGrandFather() {
        return grandFather;
    }

    public void setGrandFather(ListTest grandFather) {
        this.grandFather = grandFather;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"childList\":")
                .append(childList);
        sb.append(",\"grandFather\":")
                .append(grandFather);
        sb.append('}');
        return sb.toString();
    }
}
