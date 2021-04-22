package org.techtown.dagym.entity.dto;

public class LikeDto {
    private Long member_id;
    private Long board_id;
    private String bool;
    private int recomment_cnt;

    public LikeDto(Long member_id, Long board_id, String bool, int recomment_cnt) {
        this.member_id = member_id;
        this.board_id = board_id;
        this.bool = bool;
        this.recomment_cnt = recomment_cnt;
    }

    public LikeDto(Long member_id, Long board_id) {
        this.member_id = member_id;
        this.board_id = board_id;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }

    public int getRecomment_cnt() {
        return recomment_cnt;
    }

    public void setRecomment_cnt(int recomment_cnt) {
        this.recomment_cnt = recomment_cnt;
    }
}
