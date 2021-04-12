package org.techtown.dagym.entity.dto;

public class BoardToAdapterDto {
    private String title;
    private String user_id;
    private String content;
    private String modifiedDate;

    public BoardToAdapterDto(String title, String user_id, String content, String modifiedDate) {
        this.title = title;
        this.user_id = user_id;
        this.content = content;
        this.modifiedDate = modifiedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
