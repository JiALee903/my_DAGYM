package org.techtown.dagym;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import org.techtown.dagym.databinding.ActivityWriteBoardBinding;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.BoardListResponseDto;
import org.techtown.dagym.entity.dto.BoardSaveDto;
import org.techtown.dagym.session.SharedPreference;
import org.techtown.dagym.ui.board.RecyclerAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardInsert extends Activity {

    private ActivityWriteBoardBinding b;

    DataService dataService = new DataService();
    BoardFragment boardFragment;

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

            dataService.insert.insertBoard(id, boardSaveDto).enqueue(new Callback<Board>() {
                @Override
                public void onResponse(Call<Board> call, Response<Board> response) {
                    Log.i("TAG", "onResponse: dhodkseotlqkf");
                }

                @Override
                public void onFailure(Call<Board> call, Throwable t) {
                    t.printStackTrace();
                }
            });
//            boardFragment.insert();
            finish();
        });

    }
}