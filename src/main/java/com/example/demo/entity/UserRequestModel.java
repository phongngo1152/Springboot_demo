package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="ReadUser")
public class UserRequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nameUser")
    private String nameUser;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "address")
    private String address;
    @Column(name = "birthdate")
    private LocalDate birthdate;


    public UserRequestModel() {
    }

    public UserRequestModel(Long id, String nameUser, Integer age, Integer gender, String address, LocalDate birthdate) {
        this.id = id;
        this.nameUser = nameUser;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "UserRequestModel{" +
                "id=" + id +
                ", nameUser='" + nameUser + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
