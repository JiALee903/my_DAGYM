package org.techtown.dagym.entity.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private String user_id;
    private String comments;
    private String modDate;
    private int like_check=0;
    private int dislike_check=0;

    public CommentDto(Long id, String user_id, String comments, int like_check, int dislike_check, String modDate) {
        this.id = id;
        this.user_id = user_id;
        this.comments = comments;
        this.like_check = like_check;
        this.dislike_check = dislike_check;
        this.modDate = modDate;
    }

    public CommentDto(String user_id, String comments, String modDate) {
        this.user_id = user_id;
        this.comments = comments;
        this.modDate = modDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getLike_check() {
        return like_check;
    }

    public void setLike_check(int like_check) {
        this.like_check = like_check;
    }

    public int getDislike_check() {
        return dislike_check;
    }

    public void setDislike_check(int dislike_check) {
        this.dislike_check = dislike_check;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}
