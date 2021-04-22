package org.techtown.dagym;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.databinding.BoardDetailBinding;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.FindIdDto;
import org.techtown.dagym.entity.dto.LikeDto;
import org.techtown.dagym.session.SharedPreference;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDetail extends AppCompatActivity {
    private BoardDetailBinding b;

    private long board_id;
    private long member_id;
    private String bool = "false";
    private int recomment_cnt;

    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        board_id = getIntent().getExtras().getLong("id");

        //글 수정 버튼
        b.modifyWrite.setOnClickListener(v -> {
            Intent intent = new Intent(this, BoardUpdate.class);
            intent.putExtra("id", board_id);
            startActivity(intent);
        });

        //글 삭제 버튼
        b.deleteWrite.setOnClickListener(v -> {
            dataService.delete.deleteBoard(board_id).enqueue(new Callback<Board>() {
                @Override
                public void onResponse(Call<Board> call, Response<Board> response) {

                }

                @Override
                public void onFailure(Call<Board> call, Throwable t) {

                }
            });
            finish();
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        board_id = getIntent().getExtras().getLong("id");

        String id = SharedPreference.getAttribute(getApplicationContext(), "id");
        member_id = Long.parseLong(id);
        LikeDto likeDto = new LikeDto(member_id, board_id);

        dataService.select.idSelect(likeDto).enqueue(new Callback<FindIdDto>() {
            @Override
            public void onResponse(Call<FindIdDto> call, Response<FindIdDto> response) {
                FindIdDto body = response.body();
                Log.i("TAG", "onResponse: body = " + body);
                String strDate = body.getModDate();
                LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
                String modDate = localDateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd hh:mm"));

                b.textViewTitle.setText(body.getTitle());
                b.boardContent.setText(body.getContent());
                b.textViewDay.setText(modDate);

                String user_id = SharedPreference.getAttribute(getApplicationContext(), "user_id");
                if(!user_id.equals(body.getUser_id())) {
                    b.deleteWrite.setVisibility(View.GONE);
                    b.modifyWrite.setVisibility(View.GONE);
                }

                recomment_cnt = body.getRecommend_cnt();

                // 21 04 21 23:30
                bool = body.getBool();

                if (bool.equals("true")) {
                    b.likeAmount.setTextColor(Color.parseColor("#FF0033"));
                } else if (bool.equals("false")) {
                    b.likeAmount.setTextColor(Color.parseColor("#000000"));
                }

                Log.i("TAG", "onResponse: recomment_cnt = " + recomment_cnt);

                b.likeAmount.setText("좋아요 " +recomment_cnt+"개");
            }

            @Override
            public void onFailure(Call<FindIdDto> call, Throwable t) {
                t.printStackTrace();
            }
        });

        /*좋아요 누를 시 */
        // 21 04 21 23:30
        b.likeAmount.setOnClickListener(v -> {
            likeDto.setBool(bool);
            likeDto.setRecomment_cnt(recomment_cnt);

            dataService.insert.addLike(likeDto).enqueue(new Callback<LikeDto>() {
                @Override
                public void onResponse(Call<LikeDto> call, Response<LikeDto> response) {
                    bool = response.body().getBool();
                    recomment_cnt = response.body().getRecomment_cnt();

                    if (bool.equals("true")) {
                        b.likeAmount.setTextColor(Color.parseColor("#FF0033"));
                    } else if (bool.equals("false")) {
                        b.likeAmount.setTextColor(Color.parseColor("#000000"));
                    }

                    b.likeAmount.setText("좋아요 " + recomment_cnt +"개");

                }

                @Override
                public void onFailure(Call<LikeDto> call, Throwable t) {

                }
            });

//            if (bool.equals("true")) {
//
//                bool = "false";
//                b.likeAmount.setTextColor(Color.parseColor("#000000"));
//            } else if (bool.equals("false")) {
//                bool = "true";
//                b.likeAmount.setTextColor(Color.parseColor("#FF0033"));
//            }

            // 스프링으로 값 넘겨줘야됨.
        });

//        Log.i("TAG", "onStart: member_id + board_id = " + member_id + " " + board_id);
//
//        dataService.select.selectLike(likeDto).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("TAG", "onResponse: res = " + response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });

//        dataService.select.selectLike(likeDto).enqueue(new Callback<RecommendDto>() {
//            @Override
//            public void onResponse(Call<RecommendDto> call, Response<RecommendDto> response) {
//                RecommendDto body = response.body();
//                Log.i("TAG", "onResponse: body = " + body.getRecomment_cnt());
////                String bool = body.getBool();
////                int recomment_cnt = body.getRecomment_cnt();
//
////                Log.i("TAG", "onResponse: recomment_cnt = " + recomment_cnt);
//
////                b.likeAmount.setText("좋아요 " +recomment_cnt+"개");
//            }
//
//            @Override
//            public void onFailure(Call<RecommendDto> call, Throwable t) {
//
//            }
//        });

    }
}
