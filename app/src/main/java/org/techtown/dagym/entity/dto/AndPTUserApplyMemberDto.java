package org.techtown.dagym.entity.dto;

public class AndPTUserApplyMemberDto {
    private Long id;
    private String user_name;
    private String user_id;
    private String start_date;
    private String end_date;

    public AndPTUserApplyMemberDto(Long id, String user_name, String user_id, String start_date, String end_date) {
        this.id = id;
        this.user_name = user_name;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}
