package org.techtown.dagym.entity.dto;

import org.techtown.dagym.entity.Member;

public class FindIdDto {
    private Long id;
    private String title;
    private String user_id;
    private String content;
    private Member member;

    private String regDate;
    private String modDate;

    private String bool;
    private int recommend_cnt = 0;

    public FindIdDto(Long id, String title, String user_id, String content, Member member, String regDate, String modDate, String bool, int recommend_cnt) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.content = content;
        this.member = member;
        this.regDate = regDate;
        this.modDate = modDate;
        this.bool = bool;
        this.recommend_cnt = recommend_cnt;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }

    public int getRecommend_cnt() {
        return recommend_cnt;
    }

    public void setRecommend_cnt(int recommend_cnt) {
        this.recommend_cnt = recommend_cnt;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}
