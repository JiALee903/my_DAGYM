package org.techtown.dagym.ui.inbody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.databinding.ActivityInbodyDetailBinding;
import org.techtown.dagym.entity.dto.AndInBodyDto;
import org.techtown.dagym.entity.dto.InBodyValue;
import org.techtown.dagym.session.SharedPreference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityInbodyDetail extends AppCompatActivity {
    private ActivityInbodyDetailBinding b;

    DataService dataService = new DataService();
    private InbodyAdapter adapter = new InbodyAdapter();
    private RecyclerView recyclerView;
//    ArrayList<InBodyValue> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        recyclerView = (RecyclerView) b.inbodyRecyler;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();
        String category = intent.getExtras().getString("category");

        if(category.equals("weight")) {
            b.category.setText("체중(kg)");
        } else if (category.equals("smm")) {
            b.category.setText("골격근량(kg)");
        } else if (category.equals("bfp")) {
            b.category.setText("체지방률(%)");
        } else {
            b.category.setText("기초대사량(kcal)");
        }

        String user_id = SharedPreference.getAttribute(getApplicationContext(), "user_id");

        AndInBodyDto andInBodyDto = new AndInBodyDto();
        andInBodyDto.setInBody_user_id(user_id);
        dataService.inBodyAPI.selectInbody(andInBodyDto).enqueue(new Callback<ArrayList<AndInBodyDto>>() {
            @Override
            public void onResponse(Call<ArrayList<AndInBodyDto>> call, Response<ArrayList<AndInBodyDto>> response) {
                ArrayList<AndInBodyDto> body = response.body();
                Log.i("TAG", "body.size = " + body.size());
                ArrayList<InBodyValue> list = new ArrayList<>();

                for (int i = 0; i < body.size(); i++) {
                    AndInBodyDto dto = body.get(i);
                    String inBody_date = dto.getInBody_date();
                    SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                    InBodyValue inBodyValue = new InBodyValue();
                    inBodyValue.setId(dto.getId());
                    try {
                        Date parse = test.parse(inBody_date);
                        String format = simpleDateFormat.format(parse);

                        inBodyValue.setInBody_date(format);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(category.equals("weight")) {
                        inBodyValue.setValue(dto.getInBody_weight() + "kg");
                    } else if (category.equals("smm")) {
                        inBodyValue.setValue(dto.getInBody_smm() + "kg");
                    } else if (category.equals("bfp")) {
                        inBodyValue.setValue(dto.getInBody_bfp() + "%");
                    } else {
                        inBodyValue.setValue(dto.getInBody_rmr() + "kcal");
                    }

                    list.add(inBodyValue);
                }
                adapter.addItem(list);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<AndInBodyDto>> call, Throwable t) {

            }
        });

        adapter.onClick(new InbodyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                InBodyValue item = adapter.getItem(position);
                Long id = item.getId();
                dataService.inBodyAPI.deleteInbody(id).enqueue(new Callback<ArrayList<AndInBodyDto>>() {
                    @Override
                    public void onResponse(Call<ArrayList<AndInBodyDto>> call, Response<ArrayList<AndInBodyDto>> response) {
                        ArrayList<AndInBodyDto> body = response.body();
                        Log.i("TAG", "body.size = " + body.size());
                        ArrayList<InBodyValue> list = new ArrayList<>();

                        for (int i = 0; i < body.size(); i++) {
                            AndInBodyDto dto = body.get(i);
                            String inBody_date = dto.getInBody_date();
                            SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                            InBodyValue inBodyValue = new InBodyValue();
                            inBodyValue.setId(dto.getId());
                            try {
                                Date parse = test.parse(inBody_date);
                                String format = simpleDateFormat.format(parse);

                                inBodyValue.setInBody_date(format);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if(category.equals("weight")) {
                                inBodyValue.setValue(dto.getInBody_weight() + "kg");
                            } else if (category.equals("smm")) {
                                inBodyValue.setValue(dto.getInBody_smm() + "kg");
                            } else if (category.equals("bfp")) {
                                inBodyValue.setValue(dto.getInBody_bfp() + "%");
                            } else {
                                inBodyValue.setValue(dto.getInBody_rmr() + "kcal");
                            }

                            list.add(inBodyValue);
                        }
                        adapter.addItem(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<AndInBodyDto>> call, Throwable t) {

                    }
                });
            }
        });




    }


}