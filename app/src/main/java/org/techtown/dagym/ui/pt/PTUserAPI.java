package org.techtown.dagym.ui.pt;

import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.AndMemberMypageDto;
import org.techtown.dagym.entity.dto.AndPTUserApply;
import org.techtown.dagym.entity.dto.AndPTUserApplyMemberDto;
import org.techtown.dagym.entity.dto.AndPTUserSaveDto;
import org.techtown.dagym.entity.dto.AndPTUserSearchDto;
import org.techtown.dagym.entity.dto.AndTrainerSearchDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PTUserAPI {
    @GET("ptUser/selectAll")
    Call<ArrayList<AndPTUserSearchDto>> ptSelectAll();

    @POST("ptUser/search")
    Call<ArrayList<AndPTUserSearchDto>> ptSearch(@Body AndTrainerSearchDto andTrainerSearchDto);

    @POST("findMem")
    Call<Member> findMem(@Body Long id);

    @POST("ptUser/apply")
    Call<Void> applyTo(@Body AndPTUserSaveDto andPTUserSaveDto);

    @POST("ptUser/apply/request")
    Call<Integer> requestList(@Body Long member_id);

    @POST("ptUser/apply/findMember")
    Call<ArrayList<AndPTUserApplyMemberDto>> applyMember(@Body Long member_id);

    @PUT("ptUser/apply/if")
    Call<Void> applyIf(@Body AndPTUserApply andPTUserApply);

    @GET("ptUser/find/member/{trainer_id}")
    Call<ArrayList<AndPTUserSearchDto>> selectMembers(@Path("trainer_id") Long trainer_id);

    @GET("ptUser/find/trainer/{member_id}")
    Call<AndMemberMypageDto> selectTrainers(@Path("member_id") Long member_id);
}
