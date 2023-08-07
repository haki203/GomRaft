package com.example.gomraft.dto;

import java.util.List;

public class ListPostsReponseDTO {
    private boolean status;
    private List<PostsReponseDTO> posts;

    public ListPostsReponseDTO() {
    }

    public ListPostsReponseDTO(boolean status, List<PostsReponseDTO> posts) {
        this.status = status;
        this.posts = posts;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<PostsReponseDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsReponseDTO> posts) {
        this.posts = posts;
    }

    public class PostsReponseDTO {
        private int id;
        private String image;
        private String title;
        private String content;
        private String  created_at;

        public PostsReponseDTO() {
        }

        public PostsReponseDTO(int id,String image, String title, String content, String created_at) {
            this.id = id;
            this.image = image;
            this.title = title;
            this.content = content;
            this.created_at = created_at;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
