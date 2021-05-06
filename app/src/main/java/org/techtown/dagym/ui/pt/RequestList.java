package org.techtown.dagym.ui.pt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.databinding.RequsetNotificationBinding;
import org.techtown.dagym.entity.dto.AndPTUserApply;
import org.techtown.dagym.entity.dto.AndPTUserSaveDto;
import org.techtown.dagym.entity.dto.AndPTUserSearchDto;
import org.techtown.dagym.session.SharedPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestList extends AppCompatActivity {

    private static final String TAG = "RequestList";
    private RequsetNotificationBinding b;
    private FindMemberAdapter adapter = new FindMemberAdapter();
    private RecyclerView recyclerView;
    ArrayList<AndPTUserSearchDto> list = new ArrayList<>();
    DataService dataService = new DataService();
    private String apply_if;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        recyclerView = (RecyclerView) b.ptRecyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        String sid = SharedPreference.getAttribute(getApplicationContext(), "id");
        id = Long.parseLong(sid);
        applyMember();

        adapter.onClick(new FindMemberAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String apply_if) {
                AndPTUserSearchDto item = adapter.getItem(position);
                Log.i(TAG, "onItemClick: tid = " + id);
                Log.i(TAG, "onItemClick: item = " + item.getUser_id());
                AndPTUserApply andPTUserApply = new AndPTUserApply(
                        id, item.getUser_id(), apply_if
                );

                dataService.ptUserAPI.applyIf(andPTUserApply).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        applyMember();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void applyMember() {
        dataService.ptUserAPI.applyMember(id).enqueue(new Callback<ArrayList<AndPTUserSearchDto>>() {
            @Override
            public void onResponse(Call<ArrayList<AndPTUserSearchDto>> call, Response<ArrayList<AndPTUserSearchDto>> response) {
                list.clear();
                adapter.addList(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<AndPTUserSearchDto>> call, Throwable t) {

            }
        });
    }
}
