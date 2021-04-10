package org.techtown.dagym.ui.board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.BoardInsert;
import org.techtown.dagym.DataService;
import org.techtown.dagym.MainActivity;
import org.techtown.dagym.R;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.BoardListResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardFragment extends Fragment{

//    DataService dataService = new DataService();

    private Button writeBtn;

    private ArrayList<BoardListResponseDto> mArrayList;
    private RecyclerAdapter mAdapter;
//    private MainActivity mainActivity;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        dataService.select.selectBoard().enqueue(new Callback<List<BoardListResponseDto>>() {
//            @Override
//            public void onResponse(Call<List<BoardListResponseDto>> call, Response<List<BoardListResponseDto>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<BoardListResponseDto>> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_board, container, false);

        // fragment findViewById 구하는 법, 프래그먼트는 액티비티가 아니라 getView를 통해서 findViewById를 구할 수 있음.
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.boardRecycle);
        // fragment context구하는 법 , 프래그먼트는 this나 getApplicationContext가 안먹히기 떄문에 getActivity를 통해서 구할 수 있음.
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        mAdapter = new RecyclerAdapter(mArrayList);
        recyclerView.setAdapter(mAdapter);

        // 꾸며주는 애, 구분선 지어주려고 만들어놓음
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                mLinearLayoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        writeBtn = view.findViewById(R.id.boardWriteBtn);
        writeBtn.setOnClickListener(onClickListener);
    }

    // 글쓰기 버튼
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.boardWriteBtn:
                    writeButton();
            }
        }
    };

    private void writeButton() {
        Intent intent = new Intent(getActivity(), BoardInsert.class);
        startActivity(intent);
    }
}
