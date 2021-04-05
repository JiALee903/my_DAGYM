package org.techtown.dagym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;

import org.techtown.dagym.FindidActivity;
import org.techtown.dagym.LoginActivity;
import org.techtown.dagym.R;

public class LoginListActivity extends AppCompatActivity {
    private ISessionCallback sessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }
    };
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_list);

        Button button = (Button) findViewById(R.id.btn_register); // 버튼 객체 참조
        button.setOnClickListener(new View.OnClickListener() { // View에 리스너를 바로 구현
            @Override // 상속 받은 메소드(onclick) 변경
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.find_id);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindidActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.find_pass);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindpwActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.d_button);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}