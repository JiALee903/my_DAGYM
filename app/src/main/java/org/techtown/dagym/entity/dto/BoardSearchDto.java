package org.techtown.dagym.entity.dto;

public class BoardSearchDto {
    private String search;
    private String head;

    public BoardSearchDto(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
