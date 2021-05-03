package org.techtown.dagym.entity.dto;

public class AndPTUserSearchDto {
    private Long id;
    private String user_name;
    private String user_id;
    private String user_email;
    private String user_pn;

    public AndPTUserSearchDto(Long id, String user_name, String user_id, String user_email, String user_pn) {
        this.id = id;
        this.user_name = user_name;
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_pn = user_pn;
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

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pn() {
        return user_pn;
    }

    public void setUser_pn(String user_pn) {
        this.user_pn = user_pn;
    }
}
