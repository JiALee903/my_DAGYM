package org.techtown.dagym;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.databinding.ActivityWriteBoardBinding;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.BoardListResponseDto;
import org.techtown.dagym.entity.dto.BoardSaveDto;
import org.techtown.dagym.session.SharedPreference;
import org.techtown.dagym.ui.board.RecyclerAdapter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardInsert extends FragmentActivity {

    private ActivityWriteBoardBinding b;

    DataService dataService = new DataService();
    BoardFragment boardFragment;
//    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());


        b.updateBoard.setOnClickListener(v -> {
            BoardSaveDto boardSaveDto = new BoardSaveDto();
            String content = b.boardContent.getText().toString();
            String title = b.boardTitle.getText().toString();

            Log.i("TAG", "onCreate: con, title = " + content + title);
            boardSaveDto.setTitle(title);
            boardSaveDto.setContent(content);
            String str = SharedPreference.getAttribute(getApplicationContext(), "id");
            Long id = Long.parseLong(str);
            Call<Board> boardCall = dataService.insert.insertBoard(id, boardSaveDto);
            boardCall.enqueue(new Callback<Board>() {
                @Override
                public void onResponse(Call<Board> call, Response<Board> response) {
                    Log.i("TAG", "onResponse: dhodkseotlqkf");
                    Board body = response.body();

                    String strDate = body.getRegDate();
                    LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
                    String modDate = localDateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd hh:mm"));
                    Board board = new Board(body.getId(), body.getTitle(), body.getUser_id(), modDate);

//                    boardFragment.addItem(board);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            BoardFragment boardFragment = new BoardFragment();

                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                          fragmentTransaction.replace(R.id.boardRecycle, boardFragment);
                            fragmentTransaction.detach(boardFragment).attach(boardFragment).commit();
                            finish();
                        }
                    }, 1000);
                }

                @Override
                public void onFailure(Call<Board> call, Throwable t) {
                    t.printStackTrace();
                }
            });



        });
    }
}