package org.techtown.dagym;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.databinding.ActivityWriteBoardBinding;
import org.techtown.dagym.databinding.BoardItemBinding;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.BoardListResponseDto;
import org.techtown.dagym.session.SharedPreference;
import org.techtown.dagym.ui.board.BoardFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardInsert extends AppCompatActivity {

    private ActivityWriteBoardBinding b;

    DataService dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.updateBoard.setOnClickListener(v -> {
            BoardListResponseDto boardListResponseDto = new BoardListResponseDto();
            String content = b.boardContent.getText().toString();
            String title = b.boardTitle.getText().toString();

            Log.i("TAG", "onCreate: con, title = " + content + title);
            boardListResponseDto.setTitle(title);
            boardListResponseDto.setContent(content);
            String str = SharedPreference.getAttribute(getApplicationContext(), "id");
            Long id = Long.parseLong(str);

            dataService.insert.insertBoard(id, boardListResponseDto).enqueue(new Callback<Board>() {
                @Override
                public void onResponse(Call<Board> call, Response<Board> response) {
                    Log.i("TAG", "onResponse: dhodkseotlqkf");
                    Intent intent = new Intent(getApplicationContext(), BoardFragment.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Board> call, Throwable t) {

                }
            });
        });

    }
}
