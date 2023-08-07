package com.example.gomraft.dto;

import java.util.List;

public class DetailReponseDTO {
    private boolean status;
    private List<DetailReponseDTO.PostsReponseDTO> posts;

    public DetailReponseDTO() {
    }

    public DetailReponseDTO(boolean status, List<DetailReponseDTO.PostsReponseDTO> posts) {
        this.status = status;
        this.posts = posts;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DetailReponseDTO.PostsReponseDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<DetailReponseDTO.PostsReponseDTO> posts) {
        this.posts = posts;
    }

    public class PostsReponseDTO {
        private int id;
        private String title;
        private String content;
        private String  created_at;

        public PostsReponseDTO() {
        }

        public PostsReponseDTO(int id, String title, String content, String created_at) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
