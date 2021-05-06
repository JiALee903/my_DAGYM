package org.techtown.dagym.entity.dto;

public class AndPTUserApply {
    private Long trainer_id;
    private String user_id;
    private String apply_if;

    public AndPTUserApply(Long trainer_id, String user_id, String apply_if) {
        this.trainer_id = trainer_id;
        this.user_id = user_id;
        this.apply_if = apply_if;
    }

    public Long getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(Long trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getApply_if() {
        return apply_if;
    }

    public void setApply_if(String apply_if) {
        this.apply_if = apply_if;
    }
}
