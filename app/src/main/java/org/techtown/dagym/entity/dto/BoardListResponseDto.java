package org.techtown.dagym.entity.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class BoardListResponseDto{
    private Long id;
    private String title;
    private String user_id;
    private String content;
    private int hit;
    private int recommends;
    private int likes;
    private String modifiedDate;


    public BoardListResponseDto(Long id, String title, String user_id, String content, int hit, int recommends, int likes, String modifiedDate) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.content = content;
        this.hit = hit;
        this.recommends = recommends;
        this.likes = likes;
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getRecommends() {
        return recommends;
    }

    public void setRecommends(int recommends) {
        this.recommends = recommends;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}

