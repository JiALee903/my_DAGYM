package org.techtown.dagym.ui.board;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.DataService;
import org.techtown.dagym.databinding.ActivityWriteBoardBinding;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.BoardSaveDto;
import org.techtown.dagym.session.SharedPreference;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 게시판 글쓰기 페이지
public class BoardInsert extends FragmentActivity {

    private ActivityWriteBoardBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        DataService dataService = new DataService();

        b.updateBoard.setOnClickListener(v -> {

            BoardSaveDto boardSaveDto = new BoardSaveDto();
            String content = b.boardContent.getText().toString();
            String title = b.boardTitle.getText().toString();

            boardSaveDto.setTitle(title);
            boardSaveDto.setContent(content);
            String str = SharedPreference.getAttribute(getApplicationContext(), "id");
            Long id = Long.parseLong(str);
            Call<Board> boardCall = dataService.boardAPI.insertBoard(id, boardSaveDto);
            boardCall.enqueue(new Callback<Board>() {
                @Override
                public void onResponse(Call<Board> call, Response<Board> response) {
                    Log.i("TAG", "onResponse: dhodkseotlqkf");

                    finish();
                }

                @Override
                public void onFailure(Call<Board> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        });
    }
}