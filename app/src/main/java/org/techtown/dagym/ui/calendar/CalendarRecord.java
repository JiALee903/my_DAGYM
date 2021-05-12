package org.techtown.dagym.ui.calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.NumberPicker;

import org.techtown.dagym.R;
import org.techtown.dagym.databinding.CalendarRecordBinding;

import java.util.Calendar;

public class CalendarRecord extends Activity {

    private int MIN_MONTH = 1;
    private int MAX_MONTH = 12;

    private int MIN_HOUR = 0;
    private int MAX_HOUR = 23;

    private int MIN_MINUTE = 0;
    private int MAX_MINUTE = 59;

    private CalendarRecordBinding b;

    private DatePickerDialog.OnDateSetListener listener;
    public Calendar calendar = Calendar.getInstance();

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.monthPicker.setSelectionDividerHeight(0);
        b.dayPicker.setSelectionDividerHeight(0);
        b.hourPicker.setSelectionDividerHeight(0);
        b.minutePicker.setSelectionDividerHeight(0);

        b.monthPicker.setMinValue(MIN_MONTH);
        b.monthPicker.setMaxValue(MAX_MONTH);

        b.hourPicker.setMinValue(MIN_HOUR);
        b.hourPicker.setMaxValue(MAX_HOUR);

        b.minutePicker.setMinValue(MIN_MINUTE);
        b.minutePicker.setMaxValue(MAX_MINUTE);

        b.dayPicker.setMinValue(1);

        switch (b.monthPicker.getValue()) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                b.dayPicker.setMaxValue(31);
                break;
            case 4: case 6: case 9: case 11:
                b.dayPicker.setMaxValue(30);
                break;
            case 2:
                b.dayPicker.setMaxValue(29);
                break;
        }

        b.monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                switch (b.monthPicker.getValue()) {
                    case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                        b.dayPicker.setMaxValue(31);
                        break;
                    case 4: case 6: case 9: case 11:
                        b.dayPicker.setMaxValue(30);
                        break;
                    case 2:
                        b.dayPicker.setMaxValue(29);
                        break;
                }
            }
        });

        b.start.setOnClickListener(v -> {
            b.time.setVisibility(View.VISIBLE);
        });


    }

}
