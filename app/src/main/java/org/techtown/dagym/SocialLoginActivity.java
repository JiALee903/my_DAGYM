package org.techtown.dagym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;

import org.techtown.dagym.databinding.ActivitySocialBinding;
import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.MemberRegisterDto;
import org.techtown.dagym.session.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialLoginActivity extends Activity {
    private static final String TAG = "TEST";
    private ActivitySocialBinding b;
    private SessionCallback sessionCallback = new SessionCallback();
    Session session;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount account;
    public static final int RC_SIGN_IN = 1;
    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        // 카카오 세션
        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);
        session.checkAndImplicitOpen();

        b.kButton.setOnClickListener(v -> {

            session.open(AuthType.KAKAO_LOGIN_ALL, SocialLoginActivity.this);

//            session.op
        });

        // 구글
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient =GoogleSignIn.getClient(this,gso);

        b.gButton.setOnClickListener(v ->

        {
            switch (v.getId()) {
                case R.id.g_button:
                    Log.d("@@@@", "@@@@");
                    signIn();
                    break;
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
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

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

            MemberRegisterDto memberRegisterDto = new MemberRegisterDto();
            memberRegisterDto.setUser_email(email);
            memberRegisterDto.setUser_id(user_id);
            memberRegisterDto.setUser_name(m2);
            dataService.insert.insertOne(memberRegisterDto).enqueue(new Callback<Member>() {
                @Override
                public void onResponse(Call<Member> call, Response<Member> response) {
                    Long id = response.body().getId();

                    SharedPreference.setAttribute(getApplicationContext(), "user_id", response.body().getUser_id());
                    SharedPreference.setAttribute(getApplicationContext(), "id", response.body().getId().toString());
                    SharedPreference.setAttribute(getApplicationContext(), "user_name", response.body().getUser_name());

                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                    // DAGYM 로그인 페이지로 이동
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Member> call, Throwable t) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test() {
        Log.e(TAG, "test: hihihihihi");
    }
}
