package org.techtown.dagym.ui.inbody;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.entity.dto.AndInBodyDto;
import org.techtown.dagym.session.SharedPreference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InbodyFragment extends Fragment {

    private static final String TAG = "InBodyFragment";
    DataService dataService = new DataService();
    LineChart lineChart, lineChart2, lineChart3, lineChart4;
    ArrayList<AndInBodyDto> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbody, container, false);

        TextView sessionId = view.findViewById(R.id.session_inbody);

        String user_name = SharedPreference.getAttribute(getContext(), "user_name");
        sessionId.setText(user_name + " 님의 기록");


        View viewById = view.findViewById(R.id.inBodySubmit);
        viewById.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), InbodySubmit.class);
            startActivity(intent);
        });

        // 체중
        lineChart = (LineChart) view.findViewById(R.id.line_chart);
        // 골격근량
        lineChart2 = (LineChart) view.findViewById(R.id.line_chart2);
        // 체지방량
        lineChart3 = (LineChart) view.findViewById(R.id.line_chart3);
        // 체지방률
        lineChart4 = (LineChart) view.findViewById(R.id.line_chart4);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        List<Entry> entries = new ArrayList<>();
        List<Entry> entries2 = new ArrayList<>();
        List<Entry> entries3 = new ArrayList<>();
        List<Entry> entries4 = new ArrayList<>();

        String user_id = SharedPreference.getAttribute(getContext(), "user_id");

        AndInBodyDto andInBodyDto = new AndInBodyDto();
        andInBodyDto.setInBody_user_id(user_id);

        dataService.inBodyAPI.selectInbody(andInBodyDto).enqueue(new Callback<ArrayList<AndInBodyDto>>() {
            @Override
            public void onResponse(Call<ArrayList<AndInBodyDto>> call, Response<ArrayList<AndInBodyDto>> response) {
                list.clear();
                list = response.body();

                for (int i = 0; i < list.size(); i++) {
                    //엔트리 1 체중, 2 골격근량 3 체지방률 4 기초대사량
                    String inBody_weight = list.get(i).getInBody_weight();
                    float weight = Float.parseFloat(inBody_weight);
                    String inBody_date = list.get(i).getInBody_date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parse;
                    long time = 0;
                    try {
                        parse = format.parse(inBody_date);
                        Log.i(TAG, "onResponse: parse = " + parse);
                        time = parse.getTime();
                        Log.i(TAG, "onResponse: time = " + time);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    entries.add(new Entry(time, weight));

                    String inBody_smm = list.get(i).getInBody_smm();
                    float smm = Float.parseFloat(inBody_smm);
                    entries2.add(new Entry(time, smm));

                    String inBody_bfp = list.get(i).getInBody_bfp();
                    float bfp = Float.parseFloat(inBody_bfp);
                    entries3.add(new Entry(time, bfp));

                    String inBody_rmr = list.get(i).getInBody_rmr();
                    float rmr = Float.parseFloat(inBody_rmr);
                    entries4.add(new Entry(time, rmr));
                }

                setGraph(entries, entries2, entries3, entries4, list.size());
            }

            @Override
            public void onFailure(Call<ArrayList<AndInBodyDto>> call, Throwable t) {

            }
        });

        lineChart.setOnClickListener(v -> {
            Log.i(TAG, "onResume: gogogogo");
            Intent intent = new Intent(getContext(), ActivityInbodyDetail.class);
            intent.putExtra("category", "weight");
            startActivity(intent);
        });
        lineChart2.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityInbodyDetail.class);
            intent.putExtra("category", "smm");
            startActivity(intent);
        });
        lineChart3.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityInbodyDetail.class);
            intent.putExtra("category", "bfp");
            startActivity(intent);
        });
        lineChart4.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityInbodyDetail.class);
            intent.putExtra("category", "rmr");
            startActivity(intent);
        });
    }

    private void setGraph(List<Entry> entries, List<Entry> entries2, List<Entry> entries3, List<Entry> entries4, int size) {
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setColor(Color.parseColor("#6495ED"));
        lineDataSet.setCircleColor(Color.parseColor("#6495ED"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setValueTextSize(10.0f);
        lineDataSet.setDrawValues(true);


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

        lineChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {

                Date date = new Date((long)value);
                Log.i(TAG, "getFormattedValue: date = " + date);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                String format = simpleDateFormat.format(date);
                return format;
            }
        });

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.invalidate();

        LineDataSet lineDataSet2 = new LineDataSet(entries2, "");
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setCircleRadius(6);
        lineDataSet2.setColor(Color.parseColor("#20B2AA"));
        lineDataSet2.setCircleColor(Color.parseColor("#20B2AA"));
        lineDataSet2.setDrawCircleHole(true);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setDrawHorizontalHighlightIndicator(false);
        lineDataSet2.setDrawHighlightIndicators(false);
        lineDataSet2.setValueTextSize(10.0f);
        lineDataSet2.setDrawValues(true);

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

        lineChart2.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {

                Date date = new Date((long)value);
                Log.i(TAG, "getFormattedValue: date = " + date);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                String format = simpleDateFormat.format(date);
                return format;
            }
        });

        LineDataSet lineDataSet3 = new LineDataSet(entries3, "");
        lineDataSet3.setLineWidth(2);
        lineDataSet3.setCircleRadius(6);
        lineDataSet3.setColor(Color.parseColor("#BA55D3"));
        lineDataSet3.setCircleColor(Color.parseColor("#BA55D3"));
        lineDataSet3.setDrawCircleHole(true);
        lineDataSet3.setDrawCircles(true);
        lineDataSet3.setDrawHorizontalHighlightIndicator(false);
        lineDataSet3.setDrawHighlightIndicators(false);
        lineDataSet3.setValueTextSize(10.0f);
        lineDataSet3.setDrawValues(true);

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

        lineChart3.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {

                Date date = new Date((long)value);
                Log.i(TAG, "getFormattedValue: date = " + date);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                String format = simpleDateFormat.format(date);
                return format;
            }
        });

        LineDataSet lineDataSet4 = new LineDataSet(entries4, "");
        lineDataSet4.setLineWidth(2);
        lineDataSet4.setCircleRadius(6);
        lineDataSet4.setColor(Color.parseColor("#BDB76B"));
        lineDataSet4.setCircleColor(Color.parseColor("#BDB76B"));
        lineDataSet4.setDrawCircleHole(true);
        lineDataSet4.setDrawCircles(true);
        lineDataSet4.setDrawHorizontalHighlightIndicator(false);
        lineDataSet4.setDrawHighlightIndicators(false);
        lineDataSet4.setValueTextSize(8.0f);
        lineDataSet4.setDrawValues(true);

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

        lineChart4.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {

                Date date = new Date((long)value);
                Log.i(TAG, "getFormattedValue: date = " + date);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                String format = simpleDateFormat.format(date);
                return format;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}