package org.techtown.dagym.entity.dto;

public class AndTrainerSearchDto {
    private String search;

    private Long member_id; // PTController의 trainerApply()
    private Long trainer_id; // PTController의 trainerApply()

    public AndTrainerSearchDto(String search, Long member_id, Long trainer_id) {
        this.search = search;
        this.member_id = member_id;
        this.trainer_id = trainer_id;
    }

    public AndTrainerSearchDto() {

    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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
