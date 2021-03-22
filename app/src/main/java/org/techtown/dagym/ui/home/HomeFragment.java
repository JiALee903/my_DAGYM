package org.techtown.dagym.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.techtown.dagym.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    BarChart barChart, barChart1;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public HomeFragment() {

    }

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

        barChart = (BarChart)view.findViewById(R.id.daychart);
        barChart1 = (BarChart)view.findViewById(R.id.weekchart);

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
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        BarDataSet barDataSet1 = new BarDataSet(entries1, "");
        barDataSet1.setColors(ColorTemplate.VORDIPLOM_COLORS);

        ArrayList<String> barFactors = new ArrayList<>();
        barFactors.add("운동량");

        ArrayList<String> barFactors1 = new ArrayList<>();
        barFactors1.add("섭취 칼로리");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);
        data.setValueTextSize(12);

        /*
        Description description = new Description();
        description.setTextColor(R.color.black);
        description.setText("DAGYMSET");
        */

        // barChart.setDescription(description);
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.invalidate();
        // barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors));

        XAxis xAxis1 = barChart1.getXAxis();
        xAxis1.setGranularity(1f);
        xAxis1.setGranularityEnabled(true);

        BarData data1 = new BarData(barDataSet1);
        data1.setBarWidth(0.9f);
        data1.setValueTextSize(12);

        /*
        Description description1 = new Description();
        description1.setTextColor(R.color.black);
        description1.setText("DAGYMSET");
        */

        // barChart1.setDescription(description);
        barChart1.setData(data1);
        barChart1.setFitBars(true);
        barChart1.invalidate();
        // barChart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors1));

        barChart.getXAxis().setEnabled(false);
        barChart1.getXAxis().setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setFormSize(10f);
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        legend.setTextColor(android.R.color.black);
        List<LegendEntry> legendEntries = new ArrayList<>();
        for(int i = 0; i < barFactors.size(); i++) {
            LegendEntry legendEntry = new LegendEntry();
            legendEntry.formColor = ColorTemplate.VORDIPLOM_COLORS[i];
            legendEntry.label = barFactors.get(i);
            legendEntries.add(legendEntry);
        }

        Legend legend1 = barChart1.getLegend();
        legend1.setFormSize(10f);
        legend1.setForm(Legend.LegendForm.LINE);
        legend1.setTextSize(12f);
        legend1.setTextColor(android.R.color.black);
        List<LegendEntry> legendEntries1 = new ArrayList<>();
        for(int i = 0; i < barFactors1.size(); i++) {
            LegendEntry legendEntry1 = new LegendEntry();
            legendEntry1.formColor = ColorTemplate.VORDIPLOM_COLORS[i];
            legendEntry1.label = barFactors1.get(i);
            legendEntries1.add(legendEntry1);
        }

        legend.setXEntrySpace(5f);
        legend.setYEntrySpace(2f);
        legend.setCustom(legendEntries);

        legend1.setXEntrySpace(5f);
        legend1.setYEntrySpace(2f);
        legend1.setCustom(legendEntries1);

        return view;
    }
}