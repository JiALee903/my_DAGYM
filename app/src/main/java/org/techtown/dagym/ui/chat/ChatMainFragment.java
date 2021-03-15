package org.techtown.dagym.ui.chat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.techtown.dagym.MainActivity;
import org.techtown.dagym.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ChatMainFragment extends AppCompatActivity {
    EditText searchName;

    Uri imgUri;//선택한 프로필 이미지 경로 Uri

    boolean isFirst= true; //앱을 처음 실행한 것인가?
    boolean isChanged= false; //프로필을 변경한 적이 있는가?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchName=findViewById(R.id.search_name);

        //폰에 저장되어 있는 프로필 읽어오기
        loadData();
        if(G.nickName!=null){
            searchName.setText(G.nickName);

            //처음이 아니다, 즉, 이미 접속한 적이 있다.
            isFirst=false;

        }
    }

    private void loadData() {
        SharedPreferences preferences=getSharedPreferences("account",MODE_PRIVATE);
        G.nickName=preferences.getString("name",null);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    imgUri= data.getData();
                    //Glide.with(this).load(imgUri).into(ivProfile);
                    //Glide는 이미지를 읽어와서 보여줄때 내 device의 외장메모리에 접근하는 퍼미션이 요구됨.
                    //(퍼미션이 없으면 이미지가 보이지 않음.)
                    //Glide를 사용할 때는 동적 퍼미션 필요함.


                    //변경된 이미지가 있다.
                    isChanged=true;
                }
                break;
        }
    }
    public void clickBtn(View view) {

        //바꾼것도 없고, 처음 접속도 아니고..
        if(!isChanged && !isFirst){
            Intent intent= new Intent(this, ChatFragment.class);
            startActivity(intent);
            finish();
        }else{
            //1. save작업
            saveData();
        }
    }

    void saveData(){
        //EditText의 닉네임 가져오기 [전역변수에]
        G.nickName= searchName.getText().toString();

        //이미지를 선택하지 않았을 수도 있으므로
        if(imgUri==null) return;

        //Firebase storage에 이미지 저장하기 위해 파일명 만들기(날짜를 기반으로)
        SimpleDateFormat sdf= new SimpleDateFormat("yyyMMddhhmmss"); //20191024111224
        String fileName= sdf.format(new Date())+".png";

        //Firebase storage에 저장하기
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();

                        //1. Firebase Database에 nickName, profileUrl을 저장
                        //firebase DB관리자 객체 소환
                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                        //'profiles'라는 이름의 자식 노드 참조 객체 얻어오기
                        DatabaseReference profileRef= firebaseDatabase.getReference("profiles");

                        //닉네임을 key 식별자로 하고 프로필 이미지의 주소를 값으로 저장
                        profileRef.child(G.nickName);

                        //2. 내 phone에 nickName, profileUrl을 저장
                        SharedPreferences preferences= getSharedPreferences("account",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();

                        editor.putString("nickName",G.nickName);

                        editor.commit();
                        //저장이 완료되었으니 ChatActivity로 전환
                        Intent intent=new Intent(ChatMainFragment.this, ChatFragment.class);
                        startActivity(intent);
                        finish();

                    }
            }//saveData() ..