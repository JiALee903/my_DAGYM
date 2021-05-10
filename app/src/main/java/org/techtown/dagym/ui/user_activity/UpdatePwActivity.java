package org.techtown.dagym.ui.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.DataService;
import org.techtown.dagym.databinding.ActivityUpdatepwBinding;
import org.techtown.dagym.entity.Member;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 비밀번호 업데이트 (비밀번호찾기)
public class UpdatePwActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private ActivityUpdatepwBinding b;
    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        Bundle bundle = getIntent().getExtras();
        Long id = 0L;
        if (bundle != null) {
            id = bundle.getLong("id");
        }
        long finalId = id;

        Log.i(TAG, "onCreate: id =" + finalId);
        b.updateBtn.setOnClickListener(v -> {
            String user_pw = b.etPasswd.getText().toString();
            String user_pw2 = b.etPasswd2.getText().toString();
            if (user_pw.equals(user_pw2)) {
                dataService.userAPI.updatePw(finalId, user_pw).enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        Toast.makeText(getApplicationContext(), "비밀번호 변경 완료.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {

                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
