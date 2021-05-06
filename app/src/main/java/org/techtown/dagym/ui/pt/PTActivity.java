package org.techtown.dagym.ui.pt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.databinding.ActivityPersonaltBinding;
import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.AndPTUserSearchDto;
import org.techtown.dagym.session.SharedPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PTActivity extends AppCompatActivity {

    private ActivityPersonaltBinding b;
    DataService dataService = new DataService();
    private String tr_if;
    private PTActivityAdapter adapter = new PTActivityAdapter();
    private RecyclerView recyclerView;
    ArrayList<AndPTUserSearchDto> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        recyclerView = (RecyclerView) b.friendrc;

//        final GradientDrawable cdrawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.ic_chat);
//        final GradientDrawable fdrawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.ic_friend);

        b.friendBtn.setOnClickListener(v -> {
            b.friend.setVisibility(View.VISIBLE);
            b.chat.setVisibility(View.GONE);

        });

        b.chatBtn.setOnClickListener(v -> {
            b.friend.setVisibility(View.GONE);
            b.chat.setVisibility(View.VISIBLE);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        String id_str = SharedPreference.getAttribute(getApplicationContext(), "id");
        long id = Long.parseLong(id_str);
        dataService.ptUserAPI.findMem(id).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                tr_if = response.body().getUser_role();
                if (tr_if.equals("trainer")) {
                    requestList(id);
                }
                b.addFriend.setOnClickListener(v -> {
                    if(tr_if.equals("trainer")) {
                        Intent intent = new Intent(getApplicationContext(), RequestList.class);
                        startActivity(intent);
                    } else if(tr_if.equals("user")) {
                        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "사용할 수 없습니다.", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });
    }

    private void requestList(long id) {
        dataService.ptUserAPI.requestList(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.i("TAG", "onResponse: " + response.body());
                if(response.body() != 0) {
                    b.requestCount.setVisibility(View.VISIBLE);
                    b.rqCount.setText(response.body()+"");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}
