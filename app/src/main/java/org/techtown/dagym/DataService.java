package org.techtown.dagym;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.MemberFindIdDto;
import org.techtown.dagym.entity.dto.MemberFindPwDto;
import org.techtown.dagym.entity.dto.MemberRegisterDto;
import org.techtown.dagym.entity.dto.MemberSignDto;

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
import retrofit2.http.Path;

public class DataService {
    private String BASE_URL = "http://192.168.1.55:8090/android/";
    // 데이터 값 테스트하고싶으면 자기 cmd에서 ipconfig치고 ipv4 주소 :8090앞에 입력해줘야됨.
    // IPv4 주소........... : 192.168.1.55
    // 참고) IPv4 주소가 2개있는데 어뎁터 와이파이 적힌거 쓰면됨.
    // 와이파이에 따라서 값 바뀌니까 집이랑 카페, 학교에서 IPv4값이 다 다를거임.
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
    public UpdateAPI update = retrofitClient.create(UpdateAPI.class);
//    DeleteAPI delete = retrofitClient.create(DeleteAPI.class);


}

interface SelectAPI {
    @GET("select")
    Call<List<Member>> selectAll();

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
}

interface InsertAPI{
//    @POST("insert")
//    Call<Member> insertOne(@Body Member member);

    @POST("insert")
    Call<Member> insertOne(@Body MemberRegisterDto memberRegisterDto);
}

interface UpdateAPI{
    @POST("update/{id}")
    Call<Member> updatePw(@Path("id") Long id, @Body String user_pw);
}