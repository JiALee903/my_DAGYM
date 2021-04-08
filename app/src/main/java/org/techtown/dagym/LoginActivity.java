package org.techtown.dagym;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import org.techtown.dagym.entity.dto.MemberSignDto;
import org.techtown.dagym.session.SharedPreference;
import org.techtown.dagym.databinding.ActivityLoginBinding;
import org.techtown.dagym.entity.Member;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Test";
    private ActivityLoginBinding b;
    private EditText login_id, login_pass;
    private FirebaseAuth firebaseAuth;
    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());


        firebaseAuth = FirebaseAuth.getInstance();
        //버튼 등록하기
        login_id = b.loginId;
        login_pass = b.loginPass;

        b.findIdL.setOnClickListener(findId -> {
            Log.i(TAG, "onCreate: findId");
            Intent intent = new Intent(getApplicationContext(), FindidActivity.class);
            startActivity(intent);
        });

        b.findPwdL.setOnClickListener(findPw -> {
            Log.i(TAG, "onCreate: findPw");
            Intent intent = new Intent(getApplicationContext(), FindpwActivity.class);
            startActivity(intent);
        });

        //로그인 버튼이 눌리면
        b.btnLogin.setOnClickListener(btnLogin -> {
            String id = login_id.getText().toString().trim();
            String pwd = login_pass.getText().toString().trim();

            MemberSignDto memberSignDto = new MemberSignDto(id, pwd);

            dataService.select.signIn(memberSignDto).enqueue(new Callback<Member>() {
                @Override
                public void onResponse(Call<Member> call, Response<Member> response) {
                    try {
                        Log.i(TAG, "onResponse: In, Member.getUser_name = " + response.body().getUser_name());
                        if (!response.body().equals(null)) {
                            Toast.makeText(getApplicationContext(), "로그인을 성공했습니다.", Toast.LENGTH_SHORT).show();
                            SharedPreference.setAttribute(getApplicationContext(), "user_id", response.body().getUser_id());
                            SharedPreference.setAttribute(getApplicationContext(), "id", response.body().getId().toString());
                            SharedPreference.setAttribute(getApplicationContext(), "user_name", response.body().getUser_name());
                            String user_name = SharedPreference.getAttribute(getApplicationContext(), "user_name");
                            Log.i(TAG, "onResponse: sessionName = " + user_name);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "가입하지 않은 아이디거나, 잘못된 비밀번호입니다.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "가입하지 않은 아이디거나, 잘못된 비밀번호입니다.", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Member> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "가입하지 않은 아이디거나, 잘못된 비밀번호입니다.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        View focusView = getCurrentFocus();
//        if (focusView != null) {
//            Rect rect = new Rect();
//            focusView.getGlobalVisibleRect(rect);
//            int x = (int) ev.getX(), y = (int) ev.getY();
//            if (!rect.contains(x, y)) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                if (imm != null)
//                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
//                focusView.clearFocus();
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }
}