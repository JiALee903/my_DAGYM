package org.techtown.dagym.ui.pt;

import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.AndPTUserSearchDto;
import org.techtown.dagym.entity.dto.AndTrainerSearchDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PTUserAPI {
    @GET("ptUser/selectAll")
    Call<ArrayList<AndPTUserSearchDto>> ptSelectAll();

    @POST("ptUser/search")
    Call<ArrayList<AndPTUserSearchDto>> ptSearch(@Body AndTrainerSearchDto andTrainerSearchDto);

    @POST("findMem")
    Call<Member> findMem(@Body Long id);
}
