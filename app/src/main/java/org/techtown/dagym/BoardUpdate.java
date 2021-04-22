package org.techtown.dagym;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.databinding.ActivityWriteBoardBinding;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.BoardListResponseDto;
import org.techtown.dagym.entity.dto.BoardSaveDto;
import org.techtown.dagym.entity.dto.FindIdDto;
import org.techtown.dagym.entity.dto.LikeDto;
import org.techtown.dagym.session.SharedPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardUpdate extends AppCompatActivity {

    private ActivityWriteBoardBinding b;

    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.updateBoard.setText("글수정");

        long board_id = getIntent().getExtras().getLong("id");
        String id = SharedPreference.getAttribute(getApplicationContext(), "id");
        Long member_id = Long.parseLong(id);
        LikeDto likeDto = new LikeDto(member_id, board_id);

        dataService.select.idSelect(likeDto).enqueue(new Callback<FindIdDto>() {
            @Override
            public void onResponse(Call<FindIdDto> call, Response<FindIdDto> response) {
                b.boardTitle.setText(response.body().getTitle());
                b.boardContent.setText(response.body().getContent());
            }

            @Override
            public void onFailure(Call<FindIdDto> call, Throwable t) {

            }
        });

        b.updateBoard.setOnClickListener(v -> {
            BoardSaveDto boardSaveDto = new BoardSaveDto();

            String board_title = b.boardTitle.getText().toString();
            String board_content = b.boardContent.getText().toString();

            boardSaveDto.setTitle(board_title);
            boardSaveDto.setContent(board_content);

            dataService.update.updateBoard(board_id, boardSaveDto).enqueue(new Callback<Board>() {
                @Override
                public void onResponse(Call<Board> call, Response<Board> response) {
                    Toast.makeText(getApplicationContext(), "글 수정이 완료되었습니다.", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<Board> call, Throwable t) {

                }
            });
            finish();
        });


    }
}
