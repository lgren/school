package com.lgren.school.student.pojo;

public class Bedroom {
    private Long id;
    private String area;
    private String building;
    private String houseNo;
    private Double rent;

    public Bedroom() {
    }

    public Bedroom(Long id, String area, String building, String houseNo, Double rent) {

        this.id = id;
        this.area = area;
        this.building = building;
        this.houseNo = houseNo;
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "Bedroom{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", building='" + building + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", rent=" + rent +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String gethouseNo() {
        return houseNo;
    }

    public void sethouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }
}
