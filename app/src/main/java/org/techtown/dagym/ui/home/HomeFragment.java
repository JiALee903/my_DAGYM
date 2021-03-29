package org.techtown.dagym.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.techtown.dagym.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    BarChart barChart, barChart1;
    PieChart pieChart;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public HomeFragment() {}

    /*
        public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
       }
        public HomeFragment() {
        // 빈 생성자(Constructor) 필요함}
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, container, false);

        barChart = (BarChart)view.findViewById(R.id.ex_chart);
        barChart1 = (BarChart)view.findViewById(R.id.kcal_chart);
        pieChart = (PieChart) view.findViewById(R.id.kcal_chart2);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 1200, ""));
        entries.add(new BarEntry(1, 800, ""));
        entries.add(new BarEntry(2, 1300, ""));
        entries.add(new BarEntry(3, 1300, ""));
        entries.add(new BarEntry(4, 700, ""));
        entries.add(new BarEntry(5, 1000, ""));
        entries.add(new BarEntry(6, 300, ""));

        List<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(0, 3000, ""));
        entries1.add(new BarEntry(1, 2000, ""));
        entries1.add(new BarEntry(2, 500, ""));
        entries1.add(new BarEntry(3, 1500, ""));
        entries1.add(new BarEntry(4, 800, ""));
        entries1.add(new BarEntry(5, 1600, ""));
        entries1.add(new BarEntry(6, 1000, ""));

        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet1 = new BarDataSet(entries1, "");
        barDataSet1.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);
        data.setValueTextSize(12);

        Legend legend = barChart.getLegend();
        legend.setTextSize(15f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        barChart.getXAxis().setEnabled(true);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);

        Legend legend1 = barChart1.getLegend();
        legend1.setTextSize(15f);
        legend1.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend1.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend1.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        barChart1.getXAxis().setEnabled(true);
        barChart1.getAxisLeft().setEnabled(false);
        barChart1.getAxisRight().setEnabled(false);
        barChart1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart1.getXAxis().setDrawGridLines(false);
        barChart1.getAxisLeft().setDrawAxisLine(false);
        barChart1.getAxisRight().setDrawAxisLine(false);
        barChart1.getAxisLeft().setDrawGridLines(false);
        barChart1.getAxisRight().setDrawGridLines(false);

        /*
        Description description = new Description();
        description.setTextColor(R.color.black);
        description.setText("총 운동량");
        */

        // barChart.setDescription(description);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        barChart.invalidate();
        // barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors));

        BarData data1 = new BarData(barDataSet1);
        data1.setBarWidth(0.9f);
        data1.setValueTextSize(12);

        /*
        Description description1 = new Description();
        description1.setTextColor(R.color.black);
        description1.setText("섭취한 칼로리");
        */

        // barChart1.setDescription(description1);
        barChart1.setData(data1);
        barChart1.getDescription().setEnabled(false);
        barChart1.invalidate();
        // barChart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors1));

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry>();

        pieEntries.add(new PieEntry(34f, "지방"));
        pieEntries.add(new PieEntry(50f, "탄수화물"));
        pieEntries.add(new PieEntry(26f, "단백질"));

        Description des = new Description();
        des.setText("하루 섭취량");
        des.setTextSize(15);
        pieChart.setDescription(des);

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        PieData data2 = new PieData((dataSet));
        data2.setValueTextSize(15f);
        data2.setValueTextColor(Color.WHITE);

        pieChart.setData(data2);

        return view;
    }
}