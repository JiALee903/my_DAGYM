package org.techtown.dagym.ui.user_activity;

import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.MemberFindIdDto;
import org.techtown.dagym.entity.dto.MemberFindPwDto;
import org.techtown.dagym.entity.dto.MemberRegisterDto;
import org.techtown.dagym.entity.dto.MemberSignDto;
import org.techtown.dagym.entity.dto.MemberUpdateDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPI {

    //select
    @POST("idChk")
    Call<String> selectIdCheck(@Body String user_id);

    @POST("findId")
    Call<Member> findId(@Body MemberFindIdDto memberFindIdDto);

    @POST("signIn")
    Call<Member> signIn(@Body MemberSignDto memberSignDto);

    @POST("findPn")
    Call<Member> findPn(@Body String user_pn);

    @POST("findPw")
    Call<Member> findPw(@Body MemberFindPwDto memberFindPwDto);

    @POST("findMem")
    Call<Member> findMem(@Body Long id);

    //insert
    @POST("insert")
    Call<Member> insertOne(@Body MemberRegisterDto memberRegisterDto);


    //update
    @POST("update/{id}")
    Call<Member> updatePw(@Path("id") Long id, @Body String user_pw);

    @PUT("mypage/{id}")
    Call<Member> mypageUpdate(@Path("id") Long id, @Body MemberUpdateDto memberUpdateDto);

    //delete
    @DELETE("userDelete/{id}")
    Call<String> userDelete(@Path("id") Long id);
}
