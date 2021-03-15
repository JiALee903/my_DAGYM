package org.techtown.dagym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.techtown.dagym.MainActivity;
import org.techtown.dagym.R;
import org.techtown.dagym.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login, btn_find_id, btn_find_pass;
    private EditText login_id, login_pass;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth =  FirebaseAuth.getInstance();
        //버튼 등록하기
        login_id = (EditText) findViewById(R.id.login_id);
        login_pass = (EditText) findViewById(R.id.login_pass);

        btn_find_id = (Button) findViewById(R.id.find_id_l);
        btn_find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindidActivity.class);
                startActivity(intent);
            }
        });

        btn_find_pass = (Button) findViewById(R.id.find_pwd_l);
        btn_find_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindpwActivity.class);
                startActivity(intent);
            }
        });

        //로그인 버튼이 눌리면
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = login_id.getText().toString().trim();
                String pwd = login_pass.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(id, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else{
                                    Toast.makeText(LoginActivity.this,"로그인 오류",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });


            }
        });
    }
}