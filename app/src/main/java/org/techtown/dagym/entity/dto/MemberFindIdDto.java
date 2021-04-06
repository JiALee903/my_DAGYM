package org.techtown.dagym.entity.dto;

public class MemberFindIdDto {
    private String user_name;
    private String user_pn;

    public MemberFindIdDto(String user_name, String user_pn) {
        this.user_name = user_name;
        this.user_pn = user_pn;
    }

    public MemberFindIdDto() {
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
}
