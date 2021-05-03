package org.techtown.dagym.ui.pt;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.databinding.TrainerInfoBinding;
import org.techtown.dagym.entity.Member;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerInfo extends Activity {

    private TrainerInfoBinding b;

    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Intent intent = getIntent();
        long id = intent.getExtras().getLong("id");
        Log.i("TAG", "id = " + id);

        dataService.ptUserAPI.findMem(id).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                b.tid.setText("아이디 : " + response.body().getUser_id());
                b.tname.setText("이름 : " + response.body().getUser_name());
                b.temail.setText("이메일 : " + response.body().getUser_email());
                b.tpn.setText("전화번호 : " + response.body().getUser_pn());
                b.tlocation.setText("헬스장 : " + response.body().getAddress_normal() + response.body().getAddress_detail());
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });

        b.no.setOnClickListener(v -> {
            finish();
        });

    }

}