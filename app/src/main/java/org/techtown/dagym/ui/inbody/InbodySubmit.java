package org.techtown.dagym.ui.inbody;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.DataService;
import org.techtown.dagym.databinding.InbodySubmitBinding;
import org.techtown.dagym.entity.InBody;
import org.techtown.dagym.session.SharedPreference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InbodySubmit extends AppCompatActivity {
    private InbodySubmitBinding b;
    TextView ib;
    private int iDay, iMonth, iYear;
    private int toDay, toMonth, toYear;
    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        ib = b.inbodyDate;
        final Calendar c = Calendar.getInstance();
        toDay = c.get(Calendar.DAY_OF_MONTH);
        toMonth = c.get(Calendar.MONTH);
        toYear = c.get(Calendar.YEAR);
        iDay = toDay;
        iMonth = toMonth;
        iYear = toYear;
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dp = new DatePickerDialog(InbodySubmit.this,
                        android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        if (year > toYear) {
                            Toast.makeText(getApplicationContext(), "오늘보다 이전의 날 입력해주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (month > toMonth) {
                                Toast.makeText(getApplicationContext(), "오늘보다 이전의 날 입력해주세요.", Toast.LENGTH_SHORT).show();
                            } else {
                                if (day > toDay) {
                                    Toast.makeText(getApplicationContext(), "오늘보다 이전의 날 입력해주세요.", Toast.LENGTH_SHORT).show();
                                } else {
                                    ib.setText(year + "년/" + (month+1) + "월/" + day + "일");
                                    iYear = year;
                                    iMonth = month;
                                    iDay = day;
                                }
                            }
                        }
                    }
                }, iYear, iMonth, iDay);
                dp.show();
            }
        });

        b.ibConfirmBtn.setOnClickListener(v -> {
            String sid = SharedPreference.getAttribute(getApplicationContext(), "id");
            long id = Long.parseLong(sid);

            String weight = b.weight.getText().toString();
            String rmr = b.rmr.getText().toString();
            String bfp = b.bfp.getText().toString();
            String smm = b.smm.getText().toString();

            String date = iYear + "-" + (iMonth+1) + "-" + iDay;

            InBody inBody = new InBody();
            inBody.setInBody_weight(weight);
            inBody.setInBody_rmr(rmr);
            inBody.setInBody_bfp(bfp);
            inBody.setInBody_smm(smm);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parse = simpleDateFormat.parse(date);
                String format = simpleDateFormat.format(parse);
                format = format + "T00:00";
                inBody.setInBody_date(format);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            dataService.inBodyAPI.saveInBody(id,inBody).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        });

    }
}
