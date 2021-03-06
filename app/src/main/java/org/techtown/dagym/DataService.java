package org.techtown.dagym;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.techtown.dagym.ui.board.BoardAPI;
import org.techtown.dagym.ui.calendar.CalendarAPI;
import org.techtown.dagym.ui.inbody.InBodyAPI;
import org.techtown.dagym.ui.pt.PTUserAPI;
import org.techtown.dagym.ui.user_activity.UserAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {
    private String BASE_URL = "http://192.168.1.49:8090/android/";
//    private String BASE_URL = "http://140.238.25.78:8090/android/";

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

    public BoardAPI boardAPI = retrofitClient.create(BoardAPI.class);
    public UserAPI userAPI = retrofitClient.create(UserAPI.class);
    public PTUserAPI ptUserAPI = retrofitClient.create(PTUserAPI.class);
    public InBodyAPI inBodyAPI = retrofitClient.create(InBodyAPI.class);
    public CalendarAPI calendarAPI = retrofitClient.create(CalendarAPI.class);
}
