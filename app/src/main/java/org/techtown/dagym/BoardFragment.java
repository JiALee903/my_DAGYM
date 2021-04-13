package org.techtown.dagym;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.BoardListResponseDto;
import org.techtown.dagym.listener.RecyclerViewItemClickListener;
import org.techtown.dagym.ui.board.RecyclerAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardFragment extends Fragment{

    DataService dataService = new DataService();

    private FloatingActionButton writeBtn;

    private ArrayList<Board> mArrayList;
    private RecyclerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_board, container, false);

        // fragment findViewById 구하는 법, 프래그먼트는 액티비티가 아니라 getView를 통해서 findViewById를 구할 수 있음.
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.boardRecycle);
        // fragment context구하는 법 , 프래그먼트는 this나 getApplicationContext가 안먹히기 떄문에 getActivity를 통해서 구할 수 있음.
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        dataService.select.selectBoard().enqueue(new Callback<ArrayList<BoardListResponseDto>>() {
            @Override
            public void onResponse(Call<ArrayList<BoardListResponseDto>> call, Response<ArrayList<BoardListResponseDto>> response) {
//                Log.i("TAG", "onResponse: arraylist" + response.body().get(0).toString());
                ArrayList<BoardListResponseDto> body = response.body();

                for (int i = 0; i < response.body().size(); i++) {
                    String strDate = body.get(i).getModifiedDate();
                    LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
                    String modDate = localDateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd hh:mm"));
                    Board board = new Board(body.get(i).getId(), body.get(i).getTitle(), body.get(i).getUser_id(), modDate);
                    mArrayList.add(board);
                    Log.i("TAG", "onCreateView: " + mArrayList);
                }
                mAdapter = new RecyclerAdapter(mArrayList);
                recyclerView.setAdapter(mAdapter);

                // 꾸며주는 애, 구분선 지어주려고 만들어놓음
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        mLinearLayoutManager.getOrientation());

                recyclerView.addItemDecoration(dividerItemDecoration);


            }

            @Override
            public void onFailure(Call<ArrayList<BoardListResponseDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getContext(), new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // 아이템 클릭시에 실행되는 이벤트 부분
                final Board board = mAdapter.getItem(position);
                Intent intent = new Intent(getContext(), BoardDetail.class);
                Log.i("TAG", "onItemClick: id = " + board.getId());

                intent.putExtra("id", board.getId());
                startActivity(intent);
            }
        }));

        return view;
    }

//    public void insert() {
//        mAdapter.notifyDataSetChanged();
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        writeBtn = view.findViewById(R.id.writeFab);
        writeBtn.setOnClickListener(onClickListener);

    }

    // 글쓰기 버튼
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.writeFab:
                    writeButton();
            }
        }
    };

    private void writeButton() {
        Intent intent = new Intent(getActivity(), BoardInsert.class);
        startActivity(intent);
    }
}
