package org.techtown.dagym.ui.timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import org.techtown.dagym.R;

public class TimerFragment extends Fragment {

    //멤버변수화
    private TextView timer;
    private ScrollView scrollView;
    private TextView record;
    private Button bt_sta,bt_rec;

    //상태를 표시하는 '상수' 지정
    //- 각각의 숫자는 독립적인 개별 '상태' 의미
    public static final int INIT = 0;//처음
    public static final int RUN = 1;//실행중
    public static final int PAUSE = 2;//정지

    //상태값을 저장하는 변수
    //- INIT은 초기값임, 그걸 status 안에 넣는다.(0을 넣은거다)
    public static int status = INIT;

    //기록할때 순서 체크를 위한 변수
    private int cnt = 1;

    //타이머 시간 값을 저장할 변수
    private long baseTime,pauseTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_timer, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //객체화
        timer = view.findViewById(R.id.timer);
        scrollView = view.findViewById(R.id.scrollView);
        record = view.findViewById(R.id.record);
        bt_sta = view.findViewById(R.id.bt_sta);
        bt_rec = view.findViewById(R.id.bt_rec);
        bt_rec.setEnabled(false);//비활성화

        bt_sta.setOnClickListener(onClickListener);
        bt_rec.setOnClickListener(onClickListener);


    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bt_sta:
                    staButton();
                    break;
                case R.id.bt_rec:
                    recButton();

            }


        }
    };

    private void staButton(){
        switch (status){
            case INIT:
                //어플리케이션이 실행되고 나서 실제로 경과된 시간...
                baseTime = SystemClock.elapsedRealtime();

                //핸들러 실행
                handler.sendEmptyMessage(0);
                bt_sta.setText("멈춤");
                bt_rec.setEnabled(true);

                //상태 변환
                status = RUN;
                break;
            case RUN:
                //핸들러 정지
                handler.removeMessages(0);

                //정지 시간 체크
                pauseTime = SystemClock.elapsedRealtime();

                bt_sta.setText("시작");
                bt_rec.setText("리셋");

                //상태변환
                status = PAUSE;
                break;
            case PAUSE:
                long reStart = SystemClock.elapsedRealtime();
                baseTime += (reStart - pauseTime);

                handler.sendEmptyMessage(0);

                bt_sta.setText("멈춤");
                bt_rec.setText("기록");

                status = RUN;
        }

    }


    private void recButton(){
        switch (status){
            case RUN:
                String timeList = record.getText().toString();
                timeList+= String.format("%2d. %s\n",cnt,getTime());

                record.setText(timeList);

                cnt++;
                break;
            case PAUSE:
                bt_sta.setText("시작");
                bt_rec.setText("기록");
                bt_rec.setEnabled(false);

                timer.setText("00:00:00");
                record.setText("");

                baseTime = 0;
                pauseTime = 0;

                cnt = 1;

                status = INIT;

        }
    }

    private String getTime(){
        //경과된 시간 체크

        long nowTime = SystemClock.elapsedRealtime();
        //시스템이 부팅된 이후의 시간?
        long overTime = nowTime - baseTime;

        long m = overTime/1000/60;
        long s = (overTime/1000)%60;
        long ms = overTime % 1000;

        String recTime = String.format("%02d:%02d:%03d",m,s,ms);

        return recTime;
    }

    Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {

            timer.setText(getTime());

            //
            handler.sendEmptyMessage(0);
        }
    };

}