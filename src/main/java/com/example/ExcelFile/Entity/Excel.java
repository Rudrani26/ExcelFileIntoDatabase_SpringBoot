package com.example.ExcelFile.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exceldata")
public class Excel {

    @Id
    private String Id;
//    private Integer Id;
    private String name;
    private String position;
    private String branch;
    private String yearClass;
    private String email;
    private String mobile;


//    public Integer getId() {
//        return Id;
//    }
//
//    public void setId(Integer id) {
//        Id = id;
//    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYearClass() {
        return yearClass;
    }

    public void setYearClass(String yearClass) {
        this.yearClass = yearClass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Excel{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", branch='" + branch + '\'' +
                ", yearClass='" + yearClass + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                '}';
    }


}
