package org.techtown.dagym.entity.dto;

import java.time.LocalDateTime;

public class AndMemberMypageDto {
    private Long id;
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_pn;
    private String user_email;
    private String address_normal;
    private String address_detail;
    private String user_rrn;
    private String user_gender;
    private String user_role;
    private String regDate, modDate;

    public AndMemberMypageDto(Long id, String user_id, String user_pw, String user_name, String user_pn, String user_email, String address_normal, String address_detail, String user_rrn, String user_gender, String user_role, String regDate, String modDate) {
        this.id = id;
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_pn = user_pn;
        this.user_email = user_email;
        this.address_normal = address_normal;
        this.address_detail = address_detail;
        this.user_rrn = user_rrn;
        this.user_gender = user_gender;
        this.user_role = user_role;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getUser_pn() {
        return user_pn;
    }

    public void setUser_pn(String user_pn) {
        this.user_pn = user_pn;
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

    public String getUser_rrn() {
        return user_rrn;
    }

    public void setUser_rrn(String user_rrn) {
        this.user_rrn = user_rrn;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}
