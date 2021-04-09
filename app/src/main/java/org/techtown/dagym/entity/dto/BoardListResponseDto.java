package org.techtown.dagym.entity.dto;

import org.techtown.dagym.entity.Member;

public class BoardListResponseDto {
    private String title;
    private  String content;

    public BoardListResponseDto() {

    }

    public BoardListResponseDto(String title, String content) {
        this.title = title;
        this.content = content;
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
}
