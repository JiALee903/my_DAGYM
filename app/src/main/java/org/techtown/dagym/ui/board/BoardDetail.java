package org.techtown.dagym.ui.board;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kakao.usermgmt.StringSet;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.databinding.BoardDetailBinding;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.CommentDto;
import org.techtown.dagym.entity.dto.FindIdDto;
import org.techtown.dagym.entity.dto.LikeDto;
import org.techtown.dagym.listener.RecyclerViewItemClickListener;
import org.techtown.dagym.session.SharedPreference;
import org.techtown.dagym.ui.board.CommentAdapter.OnItemClickListener;
import org.w3c.dom.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kakao.usermgmt.StringSet.user_id;

public class BoardDetail extends AppCompatActivity {
    private BoardDetailBinding b;

    private CommentAdapter commentAdapter = new CommentAdapter();
    private RecyclerView recyclerView;

    private long board_id;
    private long member_id;
    private String bool = "false";
    private int recomment_cnt;
    ArrayList<CommentDto> mArrayList = new ArrayList<>();
    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        recyclerView = (RecyclerView) findViewById(R.id.comment_recyclerView);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setAdapter(commentAdapter);


        board_id = getIntent().getExtras().getLong("id");

        //글 수정 버튼
        b.modifyWrite.setOnClickListener(v -> {
            Intent intent = new Intent(this, BoardUpdate.class);
            intent.putExtra("id", board_id);
            startActivity(intent);
        });

        //글 삭제 버튼
        b.deleteWrite.setOnClickListener(v -> {
            Call<Board> boardCall = dataService.boardAPI.deleteBoard(board_id);

            boardCall.enqueue(new Callback<Board>() {
                @Override
                public void onResponse(Call<Board> call, Response<Board> response) {
                    finish();
                }

                @Override
                public void onFailure(Call<Board> call, Throwable t) {

                }
            });
        });

        String user_id = SharedPreference.getAttribute(getApplicationContext(), "user_id");
        commentAdapter.getUser_id(user_id);

        // 댓글 삭제
        commentAdapter.onClick(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("TAG", "onItemClick: POSITION = " + position);
                CommentDto comment = commentAdapter.getItem(position);
                Long comment_id = comment.getId();
                Log.i("TAG", "onItemClick: comment_id = " + comment_id + " comments = " + comment.getComments());
                Call<Long> commentCall = dataService.boardAPI.deleteComment(comment_id);

                commentCall.enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        select(mArrayList);
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {

                    }
                });
                finish();
            }

        });

    }

    @Override
    public void onStart() {
        super.onStart();

        board_id = getIntent().getExtras().getLong("id");

        String id = SharedPreference.getAttribute(getApplicationContext(), "id");
        member_id = Long.parseLong(id);
        LikeDto likeDto = new LikeDto(member_id, board_id);

        Call<FindIdDto> findIdDtoCall = dataService.boardAPI.idSelect(likeDto);

        findIdDtoCall.enqueue(new Callback<FindIdDto>() {
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
                if (!user_id.equals(body.getUser_id())) {
                    b.deleteWrite.setVisibility(View.GONE);
                    b.modifyWrite.setVisibility(View.GONE);
                }

                recomment_cnt = body.getRecommend_cnt();

                // 21 04 21 23:30
                bool = body.getBool();

                if (bool.equals("true")) {
                    b.favor.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_fill_favor));
                } else if (bool.equals("false")) {
                    b.favor.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favor));
                }

                Log.i("TAG", "onResponse: recomment_cnt = " + recomment_cnt);

                b.likeAmount.setText(""+recomment_cnt);

            }

            @Override
            public void onFailure(Call<FindIdDto> call, Throwable t) {
                t.printStackTrace();
            }
        });

        // 댓글 조회
        select(mArrayList);


        /*좋아요 누를 시 */
        // 21 04 21 23:30
        b.favor.setOnClickListener(v -> {
            likeDto.setBool(bool);
            likeDto.setRecomment_cnt(recomment_cnt);
            Call<LikeDto> likeDtoCall = dataService.boardAPI.addLike(likeDto);
            likeDtoCall.enqueue(new Callback<LikeDto>() {
                @Override
                public void onResponse(Call<LikeDto> call, Response<LikeDto> response) {
                    bool = response.body().getBool();
                    recomment_cnt = response.body().getRecomment_cnt();

                    if (bool.equals("true")) {
                        b.favor.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_fill_favor));
                    } else if (bool.equals("false")) {
                        b.favor.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favor));
                    }

                    b.likeAmount.setText("" + recomment_cnt);

                }

                @Override
                public void onFailure(Call<LikeDto> call, Throwable t) {

                }
            });
        });

        // 댓글 등록
        b.commentSend.setOnClickListener(v -> {
            String user_id = SharedPreference.getAttribute(getApplicationContext(), "user_id");
            String content = b.editReply.getText().toString();
            Log.i("TAG", "onStart: 댓글등록 : board_id = " + board_id + ", user_id = " + user_id);
            dataService.boardAPI.insertComment(user_id, board_id, content).enqueue(new Callback<CommentDto>() {
                @Override
                public void onResponse(Call<CommentDto> call, Response<CommentDto> response) {
                    b.editReply.setText(null);

                    Log.i("TAG", "onResponse: = " + response.body().getComments());
                    select(mArrayList);
                }

                @Override
                public void onFailure(Call<CommentDto> call, Throwable t) {

                }
            });
        });

    }



    private void select(ArrayList<CommentDto> mArrayList) {
        Call<ArrayList<CommentDto>> arrayListCall = dataService.boardAPI.selectComment(board_id);
        arrayListCall.enqueue(new Callback<ArrayList<CommentDto>>() {
            @Override
            public void onResponse(Call<ArrayList<CommentDto>> call, Response<ArrayList<CommentDto>> response) {
                try {
                    ArrayList<CommentDto> body = response.body();
                    Log.i("TAG", "onResponse: comment = " + body.get(0).getComments());
                    mArrayList.clear();
                    int size = response.body().size();
                    b.commentAmount.setText(size+"");
                    for (int i = 0; i < body.size(); i++) {
                        String strDate = body.get(i).getModDate();
                        LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
                        String modDate = localDateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd hh:mm"));
                        CommentDto commentDto = new CommentDto(
                                body.get(i).getId(),
                                body.get(i).getUser_id(),
                                body.get(i).getComments(),
                                modDate
                        );
                        mArrayList.add(commentDto);
                        Log.i("TAG", "onResponse: for array = " + mArrayList.get(i).getComments());
                    }
                    CommentDto commentDto = new CommentDto(
                            null,
                            "",
                            "",
                            ""
                    );
                    mArrayList.add(commentDto);

                    commentAdapter.addList(mArrayList);
                    commentAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CommentDto>> call, Throwable t) {

            }
        });


    }
}
