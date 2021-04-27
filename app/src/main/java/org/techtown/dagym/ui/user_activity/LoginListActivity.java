package org.techtown.dagym.ui.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.R;
import org.techtown.dagym.databinding.ActivityLoginListBinding;

public class LoginListActivity extends AppCompatActivity {
    private ActivityLoginListBinding b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());


        Button button = (Button) findViewById(R.id.btn_register); // 버튼 객체 참조
        button.setOnClickListener(new View.OnClickListener() { // View에 리스너를 바로 구현
            @Override // 상속 받은 메소드(onclick) 변경
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.login);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Button button5 = (Button) findViewById(R.id.social);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SocialLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}