package org.techtown.dagym.ui.user_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import org.techtown.dagym.DataService;
import org.techtown.dagym.databinding.ActivityFindidBinding;
import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.MemberFindIdDto;
import org.techtown.dagym.entity.dto.MemberSignDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 아이디 찾기 페이지
public class FindidActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private ActivityFindidBinding b;
    static final int SMS_RECEIVE_PERMISSON = 1;
    int certNum;
    private Button btn_find_pass;
    DataService dataService = new DataService();
    boolean pnChk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.find2button.setOnClickListener(v -> {
            String user_name = b.etName.getText().toString();
            String user_pn = b.etPhone.getText().toString();

            MemberFindIdDto memberFindIdDto = new MemberFindIdDto();
            memberFindIdDto.setUser_name(user_name);
            memberFindIdDto.setUser_pn(user_pn);

            Log.i(TAG, "onCreate: name, pn = " + user_name + user_pn);

            if (pnChk) {
                dataService.userAPI.findId(memberFindIdDto).enqueue(new Callback<MemberSignDto>() {
                    @Override
                    public void onResponse(Call<MemberSignDto> call, Response<MemberSignDto> response) {
                        String user_id = response.body().getUser_id();
                        Log.i(TAG, "onResponse: body = " + user_id);
                        b.userId.setText(user_name + "님의 아이디는 \"" + user_id + "\"입니다.");
                    }

                    @Override
                    public void onFailure(Call<MemberSignDto> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "전화번호 인증을 진행해주세요.", Toast.LENGTH_LONG).show();
            }
        });


        // 인증 번호
        b.certBtn.setOnClickListener(num -> {
            // 인증번호 난수 값 생성
            certNum = (int) (Math.random() * 10000);
            if (certNum < 1000) {
                certNum += 1000;
            }
            Log.i(TAG, "onCreate: In");
            String phoneNo = b.etPhone.getText().toString();
            try {
                // 문자전송
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, "DAGYM 인증번호는 " + certNum + " 입니다.", null, null);
                Log.i(TAG, "인증 번호 : " + certNum);
                Toast.makeText(getApplicationContext(), "인증번호 전송 완료!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "전송 실패", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

        // 인증 버튼 클릭 시
        b.certChkBtn.setOnClickListener(ok -> {
            String okPhone = b.etPhone2.getText().toString();
            if (Integer.parseInt(okPhone) == certNum) {
                Toast.makeText(getApplicationContext(), "인증 성공", Toast.LENGTH_LONG).show();
                pnChk = true;
            } else {
                Toast.makeText(getApplicationContext(), "인증 실패", Toast.LENGTH_LONG).show();
            }
        });

        b.ibtnBacklogin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginListActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
