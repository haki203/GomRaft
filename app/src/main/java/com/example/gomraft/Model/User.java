package com.example.gomraft.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private int id;
    private String avatar;
    private String name;
    private String student_code;
    private int gender;
    private String birthday;
    private String address;
    private String course;

    public User() {
    }
// Các phương thức getter và setter

    public User(String email, int id, String avatar,String name, String student_code, int gender, String birthday, String address, String course) {
        this.email = email;
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.student_code = student_code;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
