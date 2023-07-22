package com.example.gomraft.Model;

public class ThongTin {
    private int resourceId;
    private String name, content, mota;

    public ThongTin() {
    }

    public ThongTin(int resourceId, String name, String content, String mota) {
        this.resourceId = resourceId;
        this.name = name;
        this.content = content;
        this.mota = mota;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
