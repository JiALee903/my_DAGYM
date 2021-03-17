package org.techtown.dagym.ui.calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.techtown.dagym.R;


public class CalendarFragment extends Fragment {

    private Button right, left;

    private boolean isexercsie = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_calendar, container, false);
        return view;

        //Button right = (Button) view.findViewById(R.id.right);
        //Button left = (Button) view.findViewById(R.id.left);
    }
}

