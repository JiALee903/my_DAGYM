package org.techtown.dagym.entity.dto;

public class MemberFindPwDto {
    private String user_name;
    private String user_pn;
    private String user_id;

    public MemberFindPwDto(String user_name, String user_pn, String user_id) {
        this.user_name = user_name;
        this.user_pn = user_pn;
        this.user_id = user_id;
    }

    public MemberFindPwDto() {

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pn() {
        return user_pn;
    }

    public void setUser_pn(String user_pn) {
        this.user_pn = user_pn;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
