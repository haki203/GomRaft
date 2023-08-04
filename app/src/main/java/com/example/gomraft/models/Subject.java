package com.example.gomraft.models;

public class Subject {
    private String id;
    private String name;
    private String time;
    private String room;
    private String teacherName;
    private String teacherImage;
    private String day;
//    ca học
    private String shift;

    public Subject() {
    }

    public Subject(String id, String name, String time, String room, String teacherName, String teacherImage,String day, String shift) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.room = room;
        this.teacherName = teacherName;
        this.teacherImage = teacherImage;
        this.shift = shift;
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherImage() {
        return teacherImage;
    }

    public void setTeacherImage(String teacherImage) {
        this.teacherImage = teacherImage;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
