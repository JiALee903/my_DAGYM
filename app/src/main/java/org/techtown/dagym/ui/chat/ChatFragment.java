package org.techtown.dagym.ui.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.dagym.R;
import org.techtown.dagym.ui.chat.ChatAdapter;
import org.techtown.dagym.ui.chat.G;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatFragment extends Fragment {

    EditText et;
    ListView listView;

    ArrayList<ChatItem> messageItems=new ArrayList<>();
    ChatAdapter adapter;

    //Firebase Database 관리 객체참조변수
    FirebaseDatabase firebaseDatabase;

    //'chat'노드의 참조객체 참조변수
    DatabaseReference chatRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_chat, container, false);
        return view;

//        et = (EditText) getView().findViewById(R.id.et);
//        listView = (ListView) getView().findViewById(R.id.listview);
//        adapter=new ChatAdapter(messageItems,getLayoutInflater());
//        listView.setAdapter(adapter);

//        //Firebase DB관리 객체와 'caht'노드 참조객체 얻어오기
//        firebaseDatabase= FirebaseDatabase.getInstance();
//        chatRef= firebaseDatabase.getReference("chat");
//
//
//        //firebaseDB에서 채팅 메세지들 실시간 읽어오기..
//        //'chat'노드에 저장되어 있는 데이터들을 읽어오기
//        //chatRef에 데이터가 변경되는 것으 듣는 리스너 추가
//        chatRef.addChildEventListener(new ChildEventListener() {
//            //새로 추가된 것만 줌 ValueListener는 하나의 값만 바뀌어도 처음부터 다시 값을 줌
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                //새로 추가된 데이터(값 : MessageItem객체) 가져오기
//                ChatItem messageItem= dataSnapshot.getValue(ChatItem.class);
//
//                //새로운 메세지를 리스뷰에 추가하기 위해 ArrayList에 추가
//                messageItems.add(messageItem);
//
//                //리스트뷰를 갱신
//                adapter.notifyDataSetChanged();
//                listView.setSelection(messageItems.size()-1); //리스트뷰의 마지막 위치로 스크롤 위치 이동
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }
//
//    public void clickSend(View view) {
//
//        //firebase DB에 저장할 값들( 닉네임, 메세지, 시간)
//        String name = G.name;
//        String message = et.getText().toString();
//
//        //메세지 작성 시간 문자열로
//        Calendar calendar = Calendar.getInstance(); //현재 시간을 가지고 있는 객체
//        String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE); //14:16
//
//        //firebase DB에 저장할 값(MessageItem객체) 설정
//        ChatItem messageItem = new ChatItem(name, message, time);
//        //'char'노드에 MessageItem객체를 통해
//        chatRef.push().setValue(messageItem);
//
//        //EditText에 있는 글씨 지우기
//        et.setText("");
//
//        //소프트키패드를 안 보이게
//        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

        //처음 시작할때 EditText가 다른 뷰들보다 우선시 되어 포커스를 받음
        //즉, 시작부터 소프트 키패드가 올라와 있음

        //반대
        //즉, EditText를 감싼 Layout에게 포커스를 가지도록 속성 추가 -- xml
    }
}