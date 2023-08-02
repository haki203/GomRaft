package com.example.gomraft.Dto;

import java.util.List;

public class ListScheduleSubjectResponseDTO {
    private boolean status;
    private List<SubjectResponseDTO> data;

    public ListScheduleSubjectResponseDTO(boolean status, List<SubjectResponseDTO> subjectResponseDTOList) {
        this.status = status;
        this.data = subjectResponseDTOList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<SubjectResponseDTO> getSubjectResponseDTOList() {
        return data;
    }

    public void setSubjectResponseDTOList(List<SubjectResponseDTO> subjectResponseDTOList) {
        this.data = subjectResponseDTOList;
    }

    public static class SubjectResponseDTO {
        private int id;
        private String room;
        private String day;
        private String time;
        private String course_name;
        private String class_name;
        private String teacher_name;
        private int type;
        private int user_id;
        private String address;

        public SubjectResponseDTO(int id, String room, String day, String time, String course_name, String class_name, String teacher_name, int type, int user_id, String address) {
            this.id = id;
            this.room = room;
            this.day = day;
            this.time = time;
            this.course_name = course_name;
            this.class_name = class_name;
            this.teacher_name = teacher_name;
            this.type = type;
            this.user_id = user_id;
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
