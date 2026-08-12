package com.springbootproject.consultantmanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "consultants")
public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be under 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Column(nullable = false, length = 150)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9+\\-() ]{7,20}$", message = "Please enter a valid phone number")
    @Column(nullable = false, length = 20)
    private String phone;

    @NotBlank(message = "Technology is required")
    @Size(max = 100, message = "Technology must be under 100 characters")
    @Column(nullable = false, length = 100)
    private String technology;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 60, message = "Experience must be a realistic number of years")
    @Column(nullable = false)
    private Integer experience;

    @NotBlank(message = "Status is required")
    @Column(nullable = false, length = 20)
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
