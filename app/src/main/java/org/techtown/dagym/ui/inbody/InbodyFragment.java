package org.techtown.dagym.ui.inbody;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.techtown.dagym.R;
import org.techtown.dagym.session.SharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InbodyFragment extends Fragment {

    /*
    long now = System.currentTimeMillis();

    // 현재 시간을 date 변수에 저장한다.
    Date date = new Date(now);

    // 시간을 나타낼 포맷을 정한다.
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyy/MM/dd");

    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);

    TextView dateNow;
     */

    LineChart lineChart, lineChart2, lineChart3, lineChart4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbody, container, false);

        TextView sessionId = view.findViewById(R.id.session_inbody);

        String user_name = SharedPreference.getAttribute(getContext(), "user_name");
        sessionId.setText(user_name + " 님의 기록");

        /* dateNow = (TextView) view.findViewById(R.id.dateNow);
        dateNow.setText(formatDate); // TextView에 현재 시간 문자열 할당
         */

        lineChart = (LineChart)view.findViewById(R.id.line_chart);
        lineChart2 = (LineChart)view.findViewById(R.id.line_chart2);
        lineChart3 = (LineChart)view.findViewById(R.id.line_chart3);
        lineChart4 = (LineChart)view.findViewById(R.id.line_chart4);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 60));
        entries.add(new Entry(2, 65));
        entries.add(new Entry(3, 58));
        entries.add(new Entry(4, 58));
        entries.add(new Entry(5, 62));
        entries.add(new Entry(6, 60));
        entries.add(new Entry(7, 59));

        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setColor(Color.parseColor("#6495ED"));
        lineDataSet.setCircleColor(Color.parseColor("#6495ED"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setValueTextSize(2.0f);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        Description description = new Description();
        description.setText("");

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        lineChart.getXAxis().setEnabled(true);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawAxisLine(false);
        lineChart.getAxisRight().setDrawAxisLine(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.invalidate();

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(1, (float) 22.3));
        entries2.add(new Entry(2, (float) 24.1));
        entries2.add(new Entry(3, (float) 30.2));
        entries2.add(new Entry(4, (float) 27.3));
        entries2.add(new Entry(5, (float) 25.2));
        entries2.add(new Entry(6, (float) 25.2));
        entries2.add(new Entry(7, (float) 23.4));

        LineDataSet lineDataSet2 = new LineDataSet(entries2, "");
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setCircleRadius(6);
        lineDataSet2.setColor(Color.parseColor("#20B2AA"));
        lineDataSet2.setCircleColor(Color.parseColor("#20B2AA"));
        lineDataSet2.setDrawCircleHole(true);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setDrawHorizontalHighlightIndicator(false);
        lineDataSet2.setDrawHighlightIndicators(false);
        lineDataSet2.setValueTextSize(2.0f);
        lineDataSet2.setDrawValues(false);

        LineData lineData2 = new LineData(lineDataSet2);
        lineChart2.setData(lineData2);

        Description description2 = new Description();
        description2.setText("");

        Legend legend2 = lineChart2.getLegend();
        legend2.setEnabled(false);

        lineChart2.getXAxis().setEnabled(true);
        lineChart2.getAxisLeft().setEnabled(false);
        lineChart2.getAxisRight().setEnabled(false);
        lineChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart2.getXAxis().setDrawGridLines(false);
        lineChart2.getAxisLeft().setDrawAxisLine(false);
        lineChart2.getAxisRight().setDrawAxisLine(false);
        lineChart2.getXAxis().setDrawGridLines(false);
        lineChart2.getAxisLeft().setDrawGridLines(false);
        lineChart2.getAxisRight().setDrawGridLines(false);

        lineChart2.setDoubleTapToZoomEnabled(false);
        lineChart2.setDrawGridBackground(false);
        lineChart2.setDescription(description2);
        lineChart2.invalidate();

        List<Entry> entries3 = new ArrayList<>();
        entries3.add(new Entry(1, (float) 12.1));
        entries3.add(new Entry(2, (float) 16.4));
        entries3.add(new Entry(3, (float) 18.9));
        entries3.add(new Entry(4, (float) 13.5));
        entries3.add(new Entry(5, (float) 15.6));
        entries3.add(new Entry(6, (float) 17.2));
        entries3.add(new Entry(7, (float) 14.7));

        LineDataSet lineDataSet3 = new LineDataSet(entries3, "");
        lineDataSet3.setLineWidth(2);
        lineDataSet3.setCircleRadius(6);
        lineDataSet3.setColor(Color.parseColor("#BA55D3"));
        lineDataSet3.setCircleColor(Color.parseColor("#BA55D3"));
        lineDataSet3.setDrawCircleHole(true);
        lineDataSet3.setDrawCircles(true);
        lineDataSet3.setDrawHorizontalHighlightIndicator(false);
        lineDataSet3.setDrawHighlightIndicators(false);
        lineDataSet3.setValueTextSize(2.0f);
        lineDataSet3.setDrawValues(false);

        LineData lineData3 = new LineData(lineDataSet3);
        lineChart3.setData(lineData3);

        Description description3 = new Description();
        description3.setText("");

        Legend legend3 = lineChart3.getLegend();
        legend3.setEnabled(false);

        lineChart3.getXAxis().setEnabled(true);
        lineChart3.getAxisLeft().setEnabled(false);
        lineChart3.getAxisRight().setEnabled(false);
        lineChart3.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart3.getXAxis().setDrawGridLines(false);
        lineChart3.getAxisLeft().setDrawAxisLine(false);
        lineChart3.getAxisRight().setDrawAxisLine(false);
        lineChart3.getXAxis().setDrawGridLines(false);
        lineChart3.getAxisLeft().setDrawGridLines(false);
        lineChart3.getAxisRight().setDrawGridLines(false);

        lineChart3.setDoubleTapToZoomEnabled(false);
        lineChart3.setDrawGridBackground(false);
        lineChart3.setDescription(description3);
        lineChart3.invalidate();

        List<Entry> entries4 = new ArrayList<>();
        entries4.add(new Entry(1, (float) 18.0));
        entries4.add(new Entry(2, (float) 21.2));
        entries4.add(new Entry(3, (float) 24.1));
        entries4.add(new Entry(4, (float) 22.8));
        entries4.add(new Entry(5, (float) 26.3));
        entries4.add(new Entry(6, (float) 25.4));
        entries4.add(new Entry(7, (float) 19.9));

        LineDataSet lineDataSet4 = new LineDataSet(entries4, "");
        lineDataSet4.setLineWidth(2);
        lineDataSet4.setCircleRadius(6);
        lineDataSet4.setColor(Color.parseColor("#BDB76B"));
        lineDataSet4.setCircleColor(Color.parseColor("#BDB76B"));
        lineDataSet4.setDrawCircleHole(true);
        lineDataSet4.setDrawCircles(true);
        lineDataSet4.setDrawHorizontalHighlightIndicator(false);
        lineDataSet4.setDrawHighlightIndicators(false);
        lineDataSet4.setValueTextSize(2.0f);
        lineDataSet4.setDrawValues(false);

        LineData lineData4 = new LineData(lineDataSet4);
        lineChart4.setData(lineData4);

        Description description4 = new Description();
        description4.setText("");

        Legend legend4 = lineChart4.getLegend();
        legend4.setEnabled(false);

        lineChart4.getXAxis().setEnabled(true);
        lineChart4.getAxisLeft().setEnabled(false);
        lineChart4.getAxisRight().setEnabled(false);
        lineChart4.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart4.getXAxis().setDrawGridLines(false);
        lineChart4.getAxisLeft().setDrawAxisLine(false);
        lineChart4.getAxisRight().setDrawAxisLine(false);
        lineChart4.getXAxis().setDrawGridLines(false);
        lineChart4.getAxisLeft().setDrawGridLines(false);
        lineChart4.getAxisRight().setDrawGridLines(false);

        lineChart4.setDoubleTapToZoomEnabled(false);
        lineChart4.setDrawGridBackground(false);
        lineChart4.setDescription(description4);
        lineChart4.invalidate();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}