package org.techtown.dagym.ui.pt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.DataService;
import org.techtown.dagym.databinding.ActivityPersonaltBinding;
import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.AndMemberMypageDto;
import org.techtown.dagym.entity.dto.AndPTUserSearchDto;
import org.techtown.dagym.session.SharedPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 친구목록, 채팅방들 보이는 페이지
public class PTActivity extends AppCompatActivity {

    private static final String TAG = "PTActivity";
    private ActivityPersonaltBinding b;
    DataService dataService = new DataService();
    private String tr_if;
    private PTActivityAdapter adapter = new PTActivityAdapter();
    private RecyclerView recyclerView;
    ArrayList<AndPTUserSearchDto> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        recyclerView = (RecyclerView) b.friendrc;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        b.friendBtn.setOnClickListener(v -> {
            b.friend.setVisibility(View.VISIBLE);
            b.chat.setVisibility(View.GONE);

        });

        b.chatBtn.setOnClickListener(v -> {
            b.friend.setVisibility(View.GONE);
            b.chat.setVisibility(View.VISIBLE);
        });

        adapter.onClick(new PTActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // 클릭리스너 - 누르면 채팅방으로 이동시켜야함.
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        String id_str = SharedPreference.getAttribute(getApplicationContext(), "id");
        long id = Long.parseLong(id_str);
        dataService.ptUserAPI.findMem(id).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                tr_if = response.body().getUser_role();
                if (tr_if.equals("trainer")) {
                    tr_requestList(id);
                    tr_memberSelect(id);
                } else if(tr_if.equals("user")) {
                    Long member_id = response.body().getId();
                    dataService.ptUserAPI.selectTrainers(member_id).enqueue(new Callback<AndMemberMypageDto>() {
                        @Override
                        public void onResponse(Call<AndMemberMypageDto> call, Response<AndMemberMypageDto> response) {
                            list.clear();
                            AndMemberMypageDto body = response.body();
                            AndPTUserSearchDto andPTUserSearchDto = new AndPTUserSearchDto(
                                    body.getId(),
                                    body.getUser_name(),
                                    body.getUser_id(),
                                    body.getUser_email(),
                                    body.getUser_pn()
                            );
                            list.add(andPTUserSearchDto);
                            adapter.addList(list);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<AndMemberMypageDto> call, Throwable t) {

                        }
                    });
                }
                b.addFriend.setOnClickListener(v -> {
                    if(tr_if.equals("trainer")) {
                        Intent intent = new Intent(getApplicationContext(), RequestList.class);
                        startActivity(intent);
                    } else if(tr_if.equals("user")) {
                        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "사용할 수 없습니다.", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });

    }

    private void tr_memberSelect(long id) {
        dataService.ptUserAPI.selectMembers(id).enqueue(new Callback<ArrayList<AndPTUserSearchDto>>() {
            @Override
            public void onResponse(Call<ArrayList<AndPTUserSearchDto>> call, Response<ArrayList<AndPTUserSearchDto>> response) {
                list.clear();
                list = response.body();
                adapter.addList(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<AndPTUserSearchDto>> call, Throwable t) {

            }
        });
    }

    private void tr_requestList(long id) {
        dataService.ptUserAPI.requestList(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.i("TAG", "onResponse: " + response.body());
                if (response.body() != 0) {
                    b.requestCount.setVisibility(View.VISIBLE);
                    b.rqCount.setText(response.body() + "");
                } else {
                    b.requestCount.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}