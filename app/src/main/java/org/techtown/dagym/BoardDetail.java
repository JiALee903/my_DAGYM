package org.techtown.dagym;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.databinding.BoardDetailBinding;
import org.techtown.dagym.entity.Board;

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

    }
}
