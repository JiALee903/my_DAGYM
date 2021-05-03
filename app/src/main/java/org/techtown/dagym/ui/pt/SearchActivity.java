package org.techtown.dagym.ui.pt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.databinding.ActivityPersonaltBinding;
import org.techtown.dagym.databinding.ActivitySearchfBinding;
import org.techtown.dagym.entity.dto.AndPTUserSearchDto;
import org.techtown.dagym.entity.dto.AndTrainerSearchDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private ActivitySearchfBinding b;
    private TrainerSearchAdapter adapter = new TrainerSearchAdapter();
    private RecyclerView recyclerView;
    ArrayList<AndPTUserSearchDto> list = new ArrayList<>();
    DataService dataService = new DataService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        recyclerView = (RecyclerView) findViewById(R.id.trainerRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        Log.i(TAG, "onCreate: in");
        selectAll();

        b.commentSend.setOnClickListener(v -> {
            String search = b.editReply.getText().toString();

            AndTrainerSearchDto andTrainerSearchDto = new AndTrainerSearchDto();
            andTrainerSearchDto.setSearch(search);

            dataService.ptUserAPI.ptSearch(andTrainerSearchDto).enqueue(new Callback<ArrayList<AndPTUserSearchDto>>() {
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
        });

//         아이템 클릭 리스너
        adapter.setOnItemClickListener(new TrainerSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(TAG, "onItemClick: " + position);
                AndPTUserSearchDto item = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), TrainerInfo.class);
                intent.putExtra("id", item.getId());
                startActivity(intent);

            }
        });

    }

    private void selectAll() {
        dataService.ptUserAPI.ptSelectAll().enqueue(new Callback<ArrayList<AndPTUserSearchDto>>() {
            @Override
            public void onResponse(Call<ArrayList<AndPTUserSearchDto>> call, Response<ArrayList<AndPTUserSearchDto>> response) {
                int size = response.body().size();
                Log.i(TAG, "onResponse: " + size);
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
}
