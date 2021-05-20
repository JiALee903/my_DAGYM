package org.techtown.dagym.ui.calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.entity.dto.AndInsertCalDto;
import org.techtown.dagym.session.SharedPreference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarFragment extends Fragment {

    DataService dataService = new DataService();

    private String day_if = "";
    MaterialCalendarView materialCalendarView;

    @Override
    public void onResume() {
        super.onResume();



        String sid = SharedPreference.getAttribute(getContext(), "id");
        long id = Long.parseLong(sid);

        dataService.calendarAPI.selectCal(id).enqueue(new Callback<ArrayList<AndInsertCalDto>>() {
            @Override
            public void onResponse(Call<ArrayList<AndInsertCalDto>> call, Response<ArrayList<AndInsertCalDto>> response) {
                ArrayList<AndInsertCalDto> body = response.body();
                Log.i("TAG", "onResponse: " + body);
                for (int j = 0; j < body.size(); j++) {
                    String start = body.get(j).getStart();
                    String end = body.get(j).getEnd();

                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                        Date startDate = format.parse(start);
                        Date endDate = format.parse(end);

                        long cal = endDate.getTime() - startDate.getTime();
                        long calDate = cal / (24 * 60 * 60 * 1000);
                        calDate = Math.abs(calDate); // 4

                        long startTime = startDate.getTime();
                        long startDay = startTime;

                        for (int i = 1; i < calDate; i++) {
                            //총 3번 반복
                            startDay = startDay + (1000 * 60 * 60 * 24);
                            Date setDate = new Date(startDay);

                            materialCalendarView.addDecorators(
                                    new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(setDate)))
                            );

                        }
                        Log.i("TAG", "onResponse: " + calDate);

                        String startFormat = format.format(startDate);
                        String endFormat = format.format(endDate);
                        materialCalendarView.addDecorators(
                                new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(startDate))),
                                new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(endDate)))
                        );
                        Log.i("TAG", "onResponse: CalendarDay.today() = " + CalendarDay.today());
                        Log.i("TAG", "onResponse: date = " + CalendarDay.from(startDate));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<ArrayList<AndInsertCalDto>> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_calendar, container, false);

        materialCalendarView = view.findViewById(R.id.calendarView);
        TextView today = view.findViewById(R.id.today);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        String date = format.format(mDate);

        today.setText(date);

        OneDayDecorator oneDayDecorator = new OneDayDecorator();

        materialCalendarView.setSelectedDate(CalendarDay.today());

        materialCalendarView.addDecorators(
                new CalendarSelector(this),
                new DayDecorator(),
                new SaturdayDecorator(),
                new SundayDecorator(),
                oneDayDecorator);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String mDate = date.getYear() + "/" + (date.getMonth() + 1) + "/" + date.getDay();
                Log.i("TAG", "onDateSelected: date = " + mDate);

                today.setText(date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDay());

                if (mDate.equals(day_if)) {
                    Log.i("TAG", "성공!");
                    Intent intent = new Intent(getContext(), CalendarRecord.class);
                    intent.putExtra("year", date.getYear());
                    intent.putExtra("month", date.getMonth() + 1);
                    intent.putExtra("day", date.getDay());
                    startActivity(intent);
                } else {
                    day_if = mDate;
                }
            }
        });



        return view;
    }
}

