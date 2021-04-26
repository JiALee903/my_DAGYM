package org.techtown.dagym;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.BoardListResponseDto;
import org.techtown.dagym.entity.dto.BoardSaveDto;
import org.techtown.dagym.entity.dto.CommentDto;
import org.techtown.dagym.entity.dto.FindIdDto;
import org.techtown.dagym.entity.dto.LikeDto;
import org.techtown.dagym.entity.dto.MemberFindIdDto;
import org.techtown.dagym.entity.dto.MemberFindPwDto;
import org.techtown.dagym.entity.dto.MemberRegisterDto;
import org.techtown.dagym.entity.dto.MemberSignDto;
import org.techtown.dagym.entity.dto.MemberUpdateDto;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class DataService {
    private String BASE_URL = "http://172.26.1.213:8090/android/";
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
    public DeleteAPI delete = retrofitClient.create(DeleteAPI.class);
}

interface SelectAPI {

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

    @POST("board/select")
    Call<ArrayList<BoardListResponseDto>> selectBoard();

    @POST("board/idSelect")
    Call<FindIdDto> idSelect(@Body LikeDto likeDto);

    @POST("board/like/select")
    Call<ArrayList<FindIdDto>> selectLike(@Body Long member_id);

    @POST("board/comment/select/{board_id}")
    Call<ArrayList<CommentDto>> selectComment(@Path("board_id") Long board_id);
}

interface InsertAPI{

    @POST("insert")
    Call<Member> insertOne(@Body MemberRegisterDto memberRegisterDto);

    @POST("board/insert/{member_id}")
    Call<Board> insertBoard(@Path("member_id") Long member_id, @Body BoardSaveDto boardSaveDto);

    @POST("board/like/add")
    Call<LikeDto> addLike(@Body LikeDto likeDto);
}

interface UpdateAPI{
    @POST("update/{id}")
    Call<Member> updatePw(@Path("id") Long id, @Body String user_pw);

    @PUT("mypage/{id}")
    Call<Member> mypageUpdate(@Path("id") Long id, @Body MemberUpdateDto memberUpdateDto);

    @PUT("board/update/{board_id}")
    Call<Board> updateBoard(@Path("board_id") Long board_id, @Body BoardSaveDto boardSaveDto);

}

interface DeleteAPI {
    @DELETE("userDelete/{id}")
    Call<String> userDelete(@Path("id") Long id);

    @DELETE("board/delete/{board_id}")
    Call<Board> deleteBoard(@Path("board_id") Long board_id);
}