package org.techtown.dagym.ui.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        View view = inflater.inflate(R.layout.fragment_board, null); // Fragment로 불러올 xml 파일을 view로 가져옵니다.
        Button button1 = (Button)view.findViewById(R.id.text); // 클릭 시 Fragment를 전환할 이벤트를 발생시킬 버튼을 정의합니다.
        Spinner searchSpinner = (Spinner)view.findViewById(R.id.spinnerSearch);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.boardRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerAdapter());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Board.class);
                startActivity(intent);
            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration); // 아이템별 구분선 지정하는 코드

        ArrayAdapter searchAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.search_list, android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(searchAdapter);

        return view;
    }
}
