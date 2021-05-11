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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.techtown.dagym.R;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class CalendarFragment extends Fragment {

    private String day_if = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_calendar, container, false);

        MaterialCalendarView materialCalendarView = view.findViewById(R.id.calendarView);

        OneDayDecorator oneDayDecorator = new OneDayDecorator();

        materialCalendarView.setSelectedDate(CalendarDay.today());

        materialCalendarView.addDecorators(
                new CalendarSelector(this),
                new DayDecorator(),
                new SaturdayDecorator(),
                new SundayDecorator(),
                oneDayDecorator,
                new EventDecorator(Color.RED, Collections.singleton(CalendarDay.today()))
        );

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String mDate = date.getYear() + "/" + (date.getMonth() + 1) + "/" + date.getDay();
                Log.i("TAG", "onDateSelected: date = " + mDate);

                if(mDate.equals(day_if)) {
                    Log.i("TAG", "성공!");
                    Intent intent = new Intent(getContext(), CalendarRecord.class);
                    startActivity(intent);
                } else {
                    day_if = mDate;
                }
            }
        });

        return view;
    }
}

