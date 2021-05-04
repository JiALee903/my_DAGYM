package org.techtown.dagym.entity.dto;

public class AndPTUserSaveDto {
    private String start_date;
    private String end_date;
    private Long member_id;
    private Long trainer_id;

    public AndPTUserSaveDto(String start_date, String end_date, Long member_id, Long trainer_id) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.member_id = member_id;
        this.trainer_id = trainer_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public Long getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(Long trainer_id) {
        this.trainer_id = trainer_id;
    }
}
