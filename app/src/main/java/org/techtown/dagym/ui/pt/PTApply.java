package org.techtown.dagym.ui.pt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import org.techtown.dagym.DataService;
import org.techtown.dagym.MainActivity;
import org.techtown.dagym.R;
import org.techtown.dagym.databinding.PtApplyBinding;
import org.techtown.dagym.entity.Member;
import org.techtown.dagym.entity.dto.AndPTUserSaveDto;
import org.techtown.dagym.session.SharedPreference;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PTApply extends AppCompatActivity {

    private static final String TAG = "PTApply";
    private PtApplyBinding b;

    DataService dataService = new DataService();

    private DatePickerDialog.OnDateSetListener startMethod, endMethod;

    private int pYear, pMonth, pDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Calendar c = Calendar.getInstance();
        pYear = c.get(Calendar.YEAR);
        pMonth = c.get(Calendar.MONTH);
        pDay = c.get(Calendar.DATE);

        this.InitalizeListener();

        String id_str = SharedPreference.getAttribute(getApplicationContext(), "id");
        long member_id = Long.parseLong(id_str);

        Intent intent = getIntent();
        long trainer_id = intent.getExtras().getLong("id");
        Log.i(TAG, "onCreate: " + trainer_id);
        b.requestBtn.setOnClickListener(v -> {
            Log.i(TAG, "onCreate: requestBtn on");
            String startDate = b.selectStartDay.getText().toString();
            String endDate = b.selectFinishDay.getText().toString();

            AndPTUserSaveDto andPTUserSaveDto = new AndPTUserSaveDto(
                    startDate,endDate,member_id,trainer_id
            );

            dataService.ptUserAPI.applyTo(andPTUserSaveDto).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.i(TAG, "onResponse: dataService success" + response.body());
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        });

        dataService.ptUserAPI.findMem(member_id).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                b.requestUserName.setText(response.body().getUser_name());
                b.requestUserID.setText(response.body().getUser_id());
                b.requestUserEmail.setText(response.body().getUser_email());
                b.requestUserPhone.setText(response.body().getUser_pn());
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });

        b.closeBtn.setOnClickListener(v -> {
            finish();
        });

    }

    public void InitalizeListener() {
        startMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                b.selectStartDay.setText(year + "년" + (monthOfYear+1) + "월" + dayOfMonth + "일");
            }
        };

        endMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                b.selectFinishDay.setText(year + "년" + (monthOfYear+1) + "월" + dayOfMonth + "일");
            }
        };
    }

    public void StartClick(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, startMethod, pYear, pMonth, pDay);
        dialog.show();
    }

    public void EndClick(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, endMethod, pYear, pMonth, pDay);
        dialog.show();
    }

}


