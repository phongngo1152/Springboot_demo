package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class UserEvent implements Serializable {
    private Long id;
    private String nameUser;
    private int age;
    private int gender;
    private String address;
    private LocalDate birthdate;

    public UserEvent() {
    }

    public UserEvent(Long id, String nameUser, int age, int gender, String address, LocalDate birthdate) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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
}
