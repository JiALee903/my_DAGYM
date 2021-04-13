package org.techtown.dagym;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.databinding.ActivityWriteBoardBinding;
import org.techtown.dagym.databinding.BoardDetailBinding;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.BoardListResponseDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDetail extends AppCompatActivity {
    private BoardDetailBinding b;

    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        long id = getIntent().getExtras().getLong("id");
        dataService.select.idSelect(id).enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                Board body = response.body();
                String strDate = body.getModDate();
                LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
                String modDate = localDateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd hh:mm"));

                b.textViewTitle.setText(body.getTitle());
                b.boardContent.setText(body.getContent());
                b.textViewDay.setText(modDate);
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                t.printStackTrace();
            }
        });

        //글 수정 버튼
        b.modifyWrite.setOnClickListener(v -> {
            Intent intent = new Intent(this, BoardUpdate.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });

        //글 삭제 버튼
        b.deleteWrite.setOnClickListener(v -> {
            dataService.delete.deleteBoard(id).enqueue(new Callback<Board>() {
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
}
