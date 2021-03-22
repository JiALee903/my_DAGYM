package org.techtown.dagym.ui.inbody;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.techtown.dagym.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InbodyFragment extends Fragment {

    long now = System.currentTimeMillis();

    // 현재 시간을 date 변수에 저장한다.
    Date date = new Date(now);

    // 시간을 나타낼 포맷을 정한다.
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");

    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);

    TextView dateNow;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbody, container, false);

        dateNow = (TextView) view.findViewById(R.id.dateNow);
        dateNow.setText(formatDate); // TextView에 현재 시간 문자열 할당

        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}