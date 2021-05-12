package org.techtown.dagym.ui.inbody;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import org.techtown.dagym.R;

import java.util.Calendar;

public class InbodyTest extends AppCompatActivity {

    EditText ib;
    private int iDate, iMonth, iYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbody_test);

        ib = findViewById(R.id.inbodyDate);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                iDate = c.get(Calendar.DATE);
                iMonth = c.get(Calendar.MONTH);
                iYear = c.get(Calendar.YEAR);

                DatePickerDialog dp = new DatePickerDialog(InbodyTest.this,
                        android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        ib.setText(year + "년/" + (month+1) + "월/" + day + "일");
                    }
                }, iYear, iMonth, iDate);
                dp.show();
            }
        });
    }
}
