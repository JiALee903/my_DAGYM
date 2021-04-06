package org.techtown.dagym.entity.dto;

public class MemberSignDto {
    private String user_id;
    private String user_pw;

    public MemberSignDto(String user_id, String user_pw) {
        this.user_id = user_id;
        this.user_pw = user_pw;
    }


}
