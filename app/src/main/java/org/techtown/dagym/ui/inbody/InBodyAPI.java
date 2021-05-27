package org.techtown.dagym.ui.inbody;

import org.techtown.dagym.entity.InBody;
import org.techtown.dagym.entity.dto.AndInBodyDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InBodyAPI {
    @POST("inbody/select")
    Call<ArrayList<AndInBodyDto>> selectInbody(@Body AndInBodyDto andInBodyDto);

    @POST("inbody/save/{member_id}")
    Call<Void> saveInBody(@Path("member_id") Long member_id, @Body InBody inBody);

    @DELETE("inbody/delete/{inbody_id}")
    Call<ArrayList<AndInBodyDto>> deleteInbody(@Path("inbody_id") Long inbody_id);
}
