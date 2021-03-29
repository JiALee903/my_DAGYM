package org.techtown.dagym.ui.calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.techtown.dagym.R;

import java.util.Calendar;

public class CalendarFragment extends Fragment {

CalendarView calendar1;
Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_calendar, container, false);
        
        calendar1 = (CalendarView) view.findViewById(R.id.calendarView);
        context = container.getContext();

        calendar1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(context, "선택되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // Button right = (Button) view.findViewById(R.id.right);
        // Button left = (Button) view.findViewById(R.id.left);

        return view;
    }
}

