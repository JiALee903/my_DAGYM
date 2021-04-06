package org.techtown.dagym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;

import org.techtown.dagym.databinding.ActivityLoginListBinding;

public class LoginListActivity extends AppCompatActivity {
    private static final String TAG = "TEST";
    private ActivityLoginListBinding b;
    private SessionCallback sessionCallback = new SessionCallback();
    Session session;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount account;
    public static final int RC_SIGN_IN = 1;
    private int requestCode;
    private int resultCode;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        // 카카오 세션
        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);

        b.kButton.setOnClickListener(v -> {
            session.open(AuthType.KAKAO_LOGIN_ALL, LoginListActivity.this);
        });

        // 구글
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        b.gButton.setOnClickListener(v -> {
            switch (v.getId()) {
                case R.id.g_button:
                    Log.d("@@@@", "@@@@");
                    signIn();
                    break;
            }
        });


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

    // 구글 로그인
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // 구글 로그인
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
//        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
//            return;
//        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            String email = account.getEmail();
            String m = account.getFamilyName();
            String m1 = account.getGivenName();
            String m2 = account.getDisplayName();
            String user_id = account.getId();


            Log.i(TAG, "handleSignInResult: email = " + email);
            Log.i(TAG, "handleSignInResult: FName = " + m);
            Log.i(TAG, "handleSignInResult: GName = " + m1);
            Log.i(TAG, "handleSignInResult: DName = " + m2);
            Log.i(TAG, "handleSignInResult: user_id = " + user_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}