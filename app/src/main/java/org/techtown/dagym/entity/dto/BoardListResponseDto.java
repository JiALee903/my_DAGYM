package org.techtown.dagym.entity.dto;

import org.techtown.dagym.entity.Member;

public class BoardListResponseDto {
    private Long id;
    private String title;
    private String user_id;
    private  String content;
    private int hit;
    private Member member;

    public BoardListResponseDto() {

    }

    public BoardListResponseDto(Long id, String title, String user_id, String content, int hit, Member member) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.content = content;
        this.hit = hit;
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
