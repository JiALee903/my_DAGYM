package org.techtown.dagym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.techtown.dagym.databinding.ActivityRegisterBinding;
import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.MemberRegisterDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * 04/03 회원가입 완료.
 * insert 시 onresponse 부분은 작동 안하는데 에러 원인 찾는거 못함.
 * select 시는 에러 없음.
 * + 비밀번호 눈 아이콘 추가해서 비밀번호 잠깐보이게 하는 기능 추가할까 생각중
 * */

//회원가입
public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding b;
    DataService dataService = new DataService();
    private static final String TAG = "REGISTERACTIVITY";
    static final int SMS_RECEIVE_PERMISSON = 1;
    int certNum;
    private String emailValidation = "^[a-zA-X0-9]@[a-zA-Z0-9].[a-zA-Z0-9]";
    private MemberRegisterDto memberRegisterDto;


    boolean idChk = false;
    boolean pnChk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        String keyHash = com.kakao.util.helper.Utility.getKeyHash(this /* context */);

        Log.i(TAG, "onCreate: keyHash = " + keyHash);

        // 아이디값 변경 시 아이디 체크 다시실행하게 하는부분
        b.etId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                idChk = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //      Spinner
        Spinner genderSpinner = getSpinner();

        // insert 회원가입
        b.btnRegister.setOnClickListener(reg -> {
            register(genderSpinner);
        });

        //권한이 부여되어 있는지 확인
        int permissonCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (permissonCheck == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(getApplicationContext(), "SMS 수신권한 있음", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getApplicationContext(), "SMS 수신권한 없음", Toast.LENGTH_SHORT).show();

            //권한설정 dialog에서 거부를 누르면
            //ActivityCompat.shouldShowRequestPermissionRationale 메소드의 반환값이 true가 된다.
            //단, 사용자가 "Don't ask again"을 체크한 경우
            //거부하더라도 false를 반환하여, 직접 사용자가 권한을 부여하지 않는 이상, 권한을 요청할 수 없게 된다.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                //이곳에 권한이 왜 필요한지 설명하는 Toast나 dialog를 띄워준 후, 다시 권한을 요청한다.
                Toast.makeText(getApplicationContext(), "SMS권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSON);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSON);
            }
        }

        // 인증 번호
        b.numberButton.setOnClickListener(num -> {
            certificationNum();
        });

        // 인증 버튼 클릭 시
        b.okButton.setOnClickListener(ok -> {
            certificationBtn();
        });

        // ID 중복 확인
        b.validateButton.setOnClickListener(val -> {
            validate();
        });

        // 이메일 형식 체크
        b.etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Pattern p = Pattern.compile("^[a-zA-X0-9]@[a-zA-Z0-9].[a-zA-Z0-9]");
                    Matcher m = p.matcher((b.etEmail).getText().toString());

                    if (!m.matches()) {
                        Toast.makeText(RegisterActivity.this, "Email형식으로 입력하세요", Toast.LENGTH_SHORT).show();
                    }

                }
            }
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

    @NotNull
    private Spinner getSpinner() {
        Spinner genderSpinner = (Spinner) b.spinnerGender;
        ArrayAdapter genderAdapter = ArrayAdapter.createFromResource(this, R.array.select_gender,
                android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        return genderSpinner;
    }

    private void validate() {
        String user_id = b.etId.getText().toString();
        Log.i(TAG, "onCreate: user_id = " + user_id);
        dataService.select.selectIdCheck(user_id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String str = response.body();
                Log.i(TAG, "onResponse: str = " + str);
                if (str.equals("NO")) {
                    Toast.makeText(getApplicationContext(), "사용가능한 아이디 입니다.", Toast.LENGTH_LONG).show();
                    idChk = true;
                } else {
                    Toast.makeText(getApplicationContext(), "사용중인 아이디 입니다.", Toast.LENGTH_LONG).show();
                    b.etId.hasFocus();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void certificationBtn() {
        String okPhone = b.etPhone2.getText().toString();

        try {
            if (Integer.parseInt(okPhone) == certNum) {
                Toast.makeText(getApplicationContext(), "인증 성공", Toast.LENGTH_LONG).show();
                pnChk = true;
            } else {
                Toast.makeText(getApplicationContext(), "인증 실패", Toast.LENGTH_LONG).show();
                b.etPhone2.setText("");
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "인증번호를 입력해주세요.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void certificationNum() {
        // 인증번호 난수 값 생성
        certNum = (int) (Math.random() * 10000);
        if (certNum < 1000) {
            certNum += 1000;
        }
        Log.i(TAG, "onCreate: In");
        String phoneNo = b.etPhone.getText().toString();
        Log.i(TAG, "phoneNo = " + phoneNo);

        dataService.select.findPn(phoneNo).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {

                try {
                    if (response.body().getUser_pn().toString().equals(phoneNo)) {
                        Toast.makeText(getApplicationContext(), "현재 휴대폰번호로 가입한 아이디가 존재합니다.", Toast.LENGTH_LONG).show();
                    } else {
                        sendSMS(phoneNo);
                    }
                } catch (NullPointerException e) {
                    sendSMS(phoneNo);
                }

            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void sendSMS(String phoneNo) {
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
    }

    private void register(Spinner genderSpinner) {
        // 트레이너 체크
        String tr_if = null;
        if (b.trainer.isChecked()) {
            tr_if = "trainer";
        } else if (b.user.isChecked()) {
            tr_if = "user";
        }

        // 성별 확인
        String gender_if = null;
        if (genderSpinner.getSelectedItem().toString().equals("남")) {
            gender_if = "1";
        } else if (genderSpinner.getSelectedItem().toString().equals("여")) {
            gender_if = "2";
        }

        // 에디트텍스트 값
        String user_id = b.etId.getText().toString();
        String user_pw = b.etPass.getText().toString();
        String user_name = b.etName.getText().toString();
        String user_pn = b.etPhone.getText().toString();
        String user_email = b.etEmail.getText().toString();
        String address_normal = b.etBasicAddr.getText().toString();
        String address_detail = b.etDetailAddr.getText().toString();
        String user_rrn = b.etBirth.getText().toString();

        // 회원가입 값 저장
        MemberRegisterDto memberRegisterDto = new MemberRegisterDto();
        memberRegisterDto.setUser_id(user_id);
        memberRegisterDto.setUser_pw(user_pw);
        memberRegisterDto.setUser_name(user_name);
        memberRegisterDto.setUser_pn(user_pn);
        memberRegisterDto.setUser_email(user_email);
        memberRegisterDto.setAddress_normal(address_normal);
        memberRegisterDto.setAddress_detail(address_detail);
        memberRegisterDto.setUser_rrn(user_rrn);
        memberRegisterDto.setUser_gender(gender_if);
        memberRegisterDto.setUser_role(tr_if);

        // 체크 값 확인 및 회원가입
        if (user_id.isEmpty()) {
            Toast.makeText(getApplicationContext(), "ID를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if (idChk == false) {
            Toast.makeText(getApplicationContext(), "ID 중복체크를 해주세요.", Toast.LENGTH_SHORT).show();
        } else if (user_pw.isEmpty()) {
            Toast.makeText(getApplicationContext(), "PASSWORD를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if (user_name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if (user_pn.isEmpty()) {
            Toast.makeText(getApplicationContext(), "휴대폰번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if (pnChk == false) {
            Toast.makeText(getApplicationContext(), "전화번호 인증을 진행해주세요.", Toast.LENGTH_SHORT).show();
        } else if (user_email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "EMAIL을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
            Toast.makeText(getApplicationContext(), "이메일 형식을 맞춰 작성해주세요.", Toast.LENGTH_SHORT).show();
        } else if (tr_if == null) {
            Toast.makeText(getApplicationContext(), "트레이너 여부를 확인해주세요.", Toast.LENGTH_SHORT).show();
        } else if (idChk == true && pnChk == true && Patterns.EMAIL_ADDRESS.matcher(user_email).matches() && tr_if != null) {
            Log.i(TAG, "onCreate: map = " + memberRegisterDto.getUser_name());

            // 웹 db 호출 및 저장
            dataService.insert.insertOne(memberRegisterDto).enqueue(new Callback<Member>() {
                @Override
                public void onResponse(Call<Member> call, Response<Member> response) {
                    Log.i(TAG, "onResponse: In");
                }

                @Override
                public void onFailure(Call<Member> call, Throwable t) {
                    Log.i(TAG, "onFailure: fail");
                    t.printStackTrace();
                }
            });
            Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();

            // DAGYM 로그인 페이지로 이동
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}