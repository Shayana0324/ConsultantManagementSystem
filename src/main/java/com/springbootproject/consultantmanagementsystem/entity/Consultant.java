package com.springbootproject.consultantmanagementsystem.entity;

import jakarta.persistence.Entity;

@Entity
public class Consultant {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String technology;
    private Integer experience;
    private String status = "Active";           // Active or Inactive

    public Consultant() {
    }

    private Consultant(String name, String email, String phone, String technology, Integer experience, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.technology = technology;
        this.experience = experience;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTechnology(){
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
