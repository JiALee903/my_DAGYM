package org.techtown.dagym;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.techtown.dagym.entity.Member;

import java.util.List;
import java.util.Map;

import lombok.val;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class DataService {
    private String BASE_URL = "http://192.168.1.55:8090/";

    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

    public SelectAPI select = retrofitClient.create(SelectAPI.class);
    public InsertAPI insert = retrofitClient.create(InsertAPI.class);
//    UpdateAPI update = retrofitClient.create(UpdateAPI.class);
//    DeleteAPI delete = retrofitClient.create(DeleteAPI.class);


}

interface SelectAPI {
    @GET("android/select")
    Call<List<Member>> selectAll();

    @POST("android/idChk")
    Call<String> selectIdCheck(@Body String user_id);
}

interface InsertAPI{
//    @POST("android/insert")
//    Call<Member> insertOne(@Body Map<String, String> map);

    @POST("android/insert")
    Call<Member> insertOne(@Body Member member);
}

//interface SAPI{
//    @GET("select/{id}")
//    Call<Member> selectOne(@Path("id") long id);
//
//    @GET("select")
//    Call<List<Member>> selectAll();
//}