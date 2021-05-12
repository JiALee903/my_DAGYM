package org.techtown.dagym.ui.calendar;

import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.AndInsertCalDto;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CalendarAPI {
    @POST("calendar/select/{member_id}")
    Call<ArrayList<AndInsertCalDto>> selectCal(@Path("member_id") Long member_id);

    @POST("calendar/insert/{member_id}")
    Call<Void> insertCal(@Body AndInsertCalDto andInsertCalDto, @Path("member_id") Long member_id);

}
