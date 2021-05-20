package org.techtown.dagym.ui.calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Toast;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.databinding.CalendarRecordBinding;
import org.techtown.dagym.entity.dto.AndInsertCalDto;
import org.techtown.dagym.session.SharedPreference;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarRecord extends Activity {

    private int sYear;
    private int eYear;
    private int sMonth;
    private int eMonth;
    private int sDay;
    private int eDay;
    private int sHour;
    private int eHour;
    private int sMinute;
    private int eMinute;

    private String fYear;
    private String fMonth;
    private String fDay;
    private String fHour;
    private String fMinute;
    private String efMonth;
    private String efDay;
    private String efHour;
    private String efMinute;

    private boolean start_if = false;
    private boolean end_if = false;
    private boolean dlfma = false;

    long now;
    Date date;

    DataService dataService = new DataService();

    // 오늘 날짜, 시간
    private CalendarRecordBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        // numberpicker 선 없애기
        b.monthPicker.setSelectionDividerHeight(0);
        b.dayPicker.setSelectionDividerHeight(0);
        b.hourPicker.setSelectionDividerHeight(0);
        b.minutePicker.setSelectionDividerHeight(0);

        b.monthPicker.setMinValue(1);
        b.monthPicker.setMaxValue(12);

        b.hourPicker.setMinValue(0);
        b.hourPicker.setMaxValue(23);

        b.minutePicker.setMinValue(0);
        b.minutePicker.setMaxValue(59);

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

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh");
        SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
        now = System.currentTimeMillis();
        date = new Date(now);
        Log.i("TAG", "onCreate: date = "+date);

        fYear = yearFormat.format(date);
        fMonth = monthFormat.format(date);
        fDay = dayFormat.format(date);
        fHour = hourFormat.format(date);
        fMinute = minuteFormat.format(date);

        sYear = Integer.parseInt(fYear);
        eYear = Integer.parseInt(fYear);
        sMonth = Integer.parseInt(fMonth);
        eMonth = Integer.parseInt(fMonth);
        sDay = Integer.parseInt(fDay);
        eDay = Integer.parseInt(fDay);
        sHour = Integer.parseInt(fHour);
        eHour = Integer.parseInt(fHour);
        sMinute = Integer.parseInt(fMinute);
        eMinute = Integer.parseInt(fMinute);

        Intent intent = getIntent();
        int start_month = intent.getExtras().getInt("month");
        int start_day = intent.getExtras().getInt("day");

        if (start_month < 10) {
            fMonth = "0" + start_month;
        } else {
            fMonth = "" + start_month;
        }
        if (start_day < 10) {
            fDay = "0" + start_day;
        } else {
            fDay = "" + start_day;
        }
        fHour="00";
        fMinute="00";
        if (start_month < 10) {
            efMonth = "0" + start_month;
        } else {
            efMonth = "" + start_month;
        }
        if (start_day < 10) {
            efDay = "0" + start_day;
        } else {
            efDay = "" + start_day;
        }
        efHour = "00";
        efMinute = "00";

        b.allTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    b.start.setText("시작   " + sYear + "-" + fMonth + "-" + fDay + " " + "00" + ":" + "00");
                    b.end.setText("종료   " + eYear + "-" + fMonth + "-" + fDay + " " + "23" + ":" + "59");
                    b.start.setEnabled(false);
                    b.end.setEnabled(false);


                }
            }
        });

        b.start.setText("시작   " + sYear + "-" + fMonth + "-" + fDay + " " + "00" + ":" + "00");
        b.end.setText("종료   " + eYear + "-" + fMonth + "-" + fDay + " " + "00" + ":" + "00");

        b.monthPicker.setValue(start_month);
        b.dayPicker.setValue(start_day);

        b.monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                if (dlfma) {
                    setStartDate();
                    if (oldVal == 12 && newVal == 1) {
                        sYear = sYear + 1;
                        b.start.setText("시작   " + sYear + "-" + fMonth + "-" + fDay + " " + fHour + ":" + fMinute);
                    } else if (oldVal == 1 && newVal == 12) {
                        sYear = sYear-1;
                        b.start.setText("시작   " + sYear + "-" + fMonth + "-" + fDay + " " + fHour + ":" + fMinute);
                    } else {
                        b.start.setText("시작   " + sYear + "-" + fMonth + "-" + fDay + " " + fHour + ":" + fMinute);
                    }
                } else {
                    setEndDate();
                    if (oldVal == 12 && newVal == 1) {
                        eYear = eYear + 1;
                        b.end.setText("종료   " + eYear + "-" + efMonth + "-" + efDay + " " + efHour + ":" + efMinute);
                    } else if (oldVal == 1 && newVal == 12) {
                        eYear = eYear - 1;
                        b.end.setText("종료   " + eYear + "-" + efMonth + "-" + efDay + " " + efHour + ":" + efMinute);
                    } else {
                        b.end.setText("종료   " + eYear + "-" + efMonth + "-" + efDay + " " + efHour + ":" + efMinute);
                    }
                }

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

        b.dayPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (dlfma) {
                    setStartDate();
                    b.start.setText("시작   " + sYear + "-" + fMonth + "-" + fDay + " " + fHour + ":" + fMinute);
                } else {
                    setEndDate();
                    b.end.setText("종료   " + eYear + "-" + efMonth + "-" + efDay + " " + efHour + ":" + efMinute);
                }
            }

        });

        b.hourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                if (dlfma) {
                    setStartDate();
                    b.start.setText("시작   " + sYear + "-" + fMonth + "-" + fDay + " " + fHour + ":" + fMinute);
                } else {
                    setEndDate();
                    b.end.setText("종료   " + eYear + "-" + efMonth + "-" + efDay + " " + efHour + ":" + efMinute);
                }
            }

        });

        b.minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                if (dlfma) {
                    setStartDate();
                    b.start.setText("시작   " + sYear + "-" + fMonth + "-" + fDay + " " + fHour + ":" + fMinute);
                } else {
                    setEndDate();
                    b.end.setText("종료   " + eYear + "-" + efMonth + "-" + efDay + " " + efHour + ":" + efMinute);
                }
            }

        });



        Log.i("TAG", "onCreate: start_if = " +start_if);

            b.start.setOnClickListener(v -> {
                if (!start_if) {
                    b.time.setVisibility(View.VISIBLE);
                    start_if = true;
                    dlfma = true;
                    end_if = false;
                    b.end.setBackground(null);
                    b.start.setBackground(getDrawable(R.drawable.timebox));
                    b.monthPicker.setValue(sMonth);
                    b.dayPicker.setValue(sDay);
                    b.hourPicker.setValue(sHour);
                    b.minutePicker.setValue(sMinute);
                    Log.i("TAG", "onCreate: start_if = " + start_if);
                } else {
                    Log.i("TAG", "onCreate: start_else_if = " + start_if);

                    b.time.setVisibility(View.GONE);
                    start_if = false;
                    b.start.setBackground(null);
                    Log.i("TAG", "onCreate: start_else_if = " + start_if);
                }
            });
            b.end.setOnClickListener(v -> {
                if (!end_if) {
                    b.time.setVisibility(View.VISIBLE);
                    start_if = false;
                    end_if = true;
                    dlfma = false;
                    b.start.setBackground(null);
                    b.end.setBackground(getDrawable(R.drawable.timebox));
                    b.monthPicker.setValue(eMonth);
                    b.dayPicker.setValue(eDay);
                    b.hourPicker.setValue(eHour);
                    b.minutePicker.setValue(eMinute);
                } else {
                    b.time.setVisibility(View.GONE);
                    end_if = false;
                    b.end.setBackground(null);
                }
            });

        b.submit.setOnClickListener(v -> {
            overCheck();
            if (b.allTime.isChecked()) {
                efHour = "23";
                efMinute = "59";
            }
            if(b.title.getText().toString().isEmpty()) {
                Toast.makeText(this, "일정명을 등록해주세요.", Toast.LENGTH_SHORT).show();
            } else {
                String start = sYear + "-" + fMonth + "-" + fDay + " " + fHour + ":" + fMinute;
                String end = sYear + "-" + efMonth + "-" + efDay + " " + efHour + ":" + efMinute;
                AndInsertCalDto andInsertCalDto = new AndInsertCalDto();
                andInsertCalDto.setTitle(b.title.getText().toString());
                andInsertCalDto.setDescription(b.description.getText().toString());
                andInsertCalDto.setStart(start);
                andInsertCalDto.setEnd(end);

                if (b.allTime.isChecked()) {
                    andInsertCalDto.setAllDay(true);
                } else {
                    andInsertCalDto.setAllDay(false);
                }

                String sid = SharedPreference.getAttribute(getApplicationContext(), "id");
                Long id = Long.parseLong(sid);
                dataService.calendarAPI.insertCal(andInsertCalDto, id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }

        });
    }

    private void overCheck() {
        if(sYear > eYear) {
            Toast.makeText(this, "시작 시간보다 빠른 날짜는 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
        } else if (sYear == eYear) {
            if (sMonth > eMonth) {
                Toast.makeText(this, "시작 시간보다 빠른 날짜는 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
            } else if (sMonth == eMonth) {
                if (sDay > eDay) {
                    Toast.makeText(this, "시작 시간보다 빠른 날짜는 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
                } else if (sDay == eDay) {
                    if (sHour > eHour) {
                        Toast.makeText(this, "시작 시간보다 빠른 날짜는 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    } else if (sHour == eHour) {
                        if (sMinute > eMinute) {
                            Toast.makeText(this, "시작 시간보다 빠른 날짜는 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        } else if (sMinute == eMinute) {
                            Toast.makeText(this, "시작 시간보다 빠른 날짜는 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    private void setStartDate() {
        CalendarRecord.this.sMonth = b.monthPicker.getValue();
        CalendarRecord.this.sDay = b.dayPicker.getValue();
        sHour = b.hourPicker.getValue();
        sMinute = b.minutePicker.getValue();

        if (CalendarRecord.this.sMonth < 10) {
            fMonth = "0"+ CalendarRecord.this.sMonth;
        } else {
            fMonth = ""+ CalendarRecord.this.sMonth;
        }

        if (CalendarRecord.this.sDay < 10) {
            fDay = "0" + CalendarRecord.this.sDay;
        } else {
            fDay = ""+ CalendarRecord.this.sDay;
        }

        if (sHour < 10) {
            fHour = "0" + sHour;
        } else {
            fHour = "" + sHour;
        }

        if (sMinute < 10) {
            fMinute = "0" + sMinute;
        } else {
            fMinute = "" + sMinute;
        }
    }
    private void setEndDate() {
        CalendarRecord.this.eMonth = b.monthPicker.getValue();
        CalendarRecord.this.eDay = b.dayPicker.getValue();
        eHour = b.hourPicker.getValue();
        eMinute = b.minutePicker.getValue();

        if(CalendarRecord.this.eMonth < 10) {
            efMonth = "0"+ CalendarRecord.this.eMonth;
        } else {
            efMonth = ""+ CalendarRecord.this.eMonth;
        }

        if (CalendarRecord.this.eDay < 10) {
            efDay = "0" + CalendarRecord.this.eDay;
        } else {
            efDay = ""+ CalendarRecord.this.eDay;
        }

        if (eHour < 10) {
            efHour = "0" + eHour;
        } else {
            efHour = "" + eHour;
        }

        if (eMinute < 10) {
            efMinute = "0" + eMinute;
        } else {
            efMinute = "" + eMinute;
        }
    }

}
