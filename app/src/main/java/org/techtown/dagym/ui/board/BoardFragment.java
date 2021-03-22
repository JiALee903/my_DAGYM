package org.techtown.dagym.ui.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.techtown.dagym.Board;
import org.techtown.dagym.MainActivity;
import org.techtown.dagym.R;

public class BoardFragment extends Fragment {

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static BoardFragment newInstance() {
        return new BoardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        View view = inflater.inflate(R.layout.fragment_board, null); // Fragment로 불러올 xml파일을 view로 가져옵니다.
        Button button1 = (Button)view.findViewById(R.id.text); // click시 Fragment를 전환할 event를 발생시킬 버튼을 정의합니다.

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Board.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
