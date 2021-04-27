package org.techtown.dagym.ui.user_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.DataService;
import org.techtown.dagym.MainActivity;
import org.techtown.dagym.R;
import org.techtown.dagym.SplashActivity;
import org.techtown.dagym.databinding.ActivityMyPageBinding;
import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.MemberUpdateDto;
import org.techtown.dagym.session.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private ActivityMyPageBinding b;

    DataService dataService = new DataService();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        

        String str = SharedPreference.getAttribute(getApplicationContext(), "id");
        Long id = Long.parseLong(str);

//        String user_id = SharedPreference.getAttribute(getApplicationContext(), "user_id");
//        b.userId.setText(user_id);
        Member member = new Member();
        dataService.userAPI.findMem(id).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member member = response.body();

                b.userId.setText(member.getUser_id());
                b.userName.setText(member.getUser_name());
                b.userEmail.setText(member.getUser_email());
                b.userBirth.setText(member.getUser_rrn());
                b.userBaddr.setText(member.getAddress_normal());
                b.userDaddr.setText(member.getAddress_detail());

                String user_role = member.getUser_role();
                try {
                    if(user_role.equals("trainer")) {
                        Log.i(TAG, "onCreate: trainer open");
                        b.userRole.check(R.id.mtrainer);
                    } else if (user_role.equals("user")) {
                        Log.i(TAG, "onCreate: user open");
                        b.userRole.check(R.id.muser);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });


        Button previous = (Button) findViewById(R.id.btn_pre);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        b.btnUpdate.setOnClickListener(v -> {
            MemberUpdateDto memberUpdateDto = new MemberUpdateDto();

            String tr_if = null;
            if (b.mtrainer.isChecked()) {
                Log.i(TAG, "onCreate: trainer save");
                tr_if = "trainer";
            } else if (b.muser.isChecked()) {
                Log.i(TAG, "onCreate: user save");
                tr_if = "user";
            }

            String user_pw = b.userPw.getText().toString();
            String user_name = b.userName.getText().toString();
            String user_email = b.userEmail.getText().toString();
            String user_baddr = b.userBaddr.getText().toString();
            String user_daddr = b.userDaddr.getText().toString();

            memberUpdateDto.setUser_pw(user_pw);
            memberUpdateDto.setUser_name(user_name);
            memberUpdateDto.setUser_email(user_email);
            memberUpdateDto.setAddress_normal(user_baddr);
            memberUpdateDto.setAddress_detail(user_daddr);
            memberUpdateDto.setUser_role(tr_if);

            if(user_pw.isEmpty()) {
                Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
//                Log.i("TAG", "onCreate: user_pw = " + user_pw);
            } else if(user_name.isEmpty()) {
                Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_LONG).show();
            } else if(user_email.isEmpty()) {
                Toast.makeText(getApplicationContext(), "이메일을 입력해주세요.", Toast.LENGTH_LONG).show();
            } else if(user_baddr.isEmpty()) {
                Toast.makeText(getApplicationContext(), "기본주소를 입력해주세요.", Toast.LENGTH_LONG).show();
            } else if(user_daddr.isEmpty()) {
                Toast.makeText(getApplicationContext(), "상세주소를 입력해주세요.", Toast.LENGTH_LONG).show();
            }
//            else if(user_role.isEmpty()) {
//                Toast.makeText(getApplicationContext(), "트레이너 여부를 체크해주세요.", Toast.LENGTH_LONG).show();
//            }
            else if(true){
                dataService.userAPI.mypageUpdate(id, memberUpdateDto).enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        Member member = response.body();

                        Log.d("update", "member : " + member);
                        Toast.makeText(getApplicationContext(), "회원 정보 수정이 완료되었습니다.", Toast.LENGTH_LONG).show();

                        SharedPreference.setAttribute(getApplicationContext(), "user_id", response.body().getUser_id());
                        SharedPreference.setAttribute(getApplicationContext(), "id", response.body().getId().toString());
                        SharedPreference.setAttribute(getApplicationContext(), "user_name", response.body().getUser_name());

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {

                    }
                });
            }
        });

        b.btnDelete.setOnClickListener(v -> {
//            Intent intent = new Intent(getBaseContext(), PopupActivity.class);
//            intent.putExtra("type", PopupType.SELECT);
//            intent.putExtra("gravity", PopupGravity.CENTER);
//            intent.putExtra("title", "회원 탈퇴");
//            intent.putExtra("content", "정말로 탈퇴하시겠습니까?");
//            intent.putExtra("buttonLeft", "예");
//            intent.putExtra("buttonRight","아니요");
//            startActivityForResult(intent, 2);

            AlertDialog.Builder popup = new AlertDialog.Builder(MyPageActivity.this);
            popup.setTitle("회원 탈퇴");
            popup.setMessage("정말로 탈퇴하시겠습니까?");

            popup.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String str = SharedPreference.getAttribute(getApplicationContext(), "id"); // 세션
                    Long id = Long.parseLong(str);
                    Log.i(TAG, "onClick: id = " + id);
                    dataService.userAPI.userDelete(id).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.i(TAG, "onResponse: " + response.body());
                            SharedPreference.removeAllAttribute(getApplicationContext());

                            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
//                    dataService.select.findMem(id)
                }
            });

            popup.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

        popup.show();
        });

    }
}
