package org.techtown.dagym.entity.dto;

public class MemberUpdateDto {
    private String user_pw;
    private String user_name;
    private String user_email;
    private String address_normal;
    private String address_detail;
    private String user_role;

    public MemberUpdateDto(String user_pw, String user_name, String user_pn, String user_email, String address_normal, String address_detail, String user_role) {
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_email = user_email;
        this.address_normal = address_normal;
        this.address_detail = address_detail;
        this.user_role = user_role;
    }

    public MemberUpdateDto() {

    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getAddress_normal() {
        return address_normal;
    }

    public void setAddress_normal(String address_normal) {
        this.address_normal = address_normal;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
