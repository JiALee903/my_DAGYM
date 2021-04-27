package org.techtown.dagym.ui.board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.BoardListResponseDto;
import org.techtown.dagym.entity.dto.BoardSearchDto;
import org.techtown.dagym.entity.dto.FindIdDto;
import org.techtown.dagym.listener.RecyclerViewItemClickListener;
import org.techtown.dagym.session.SharedPreference;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardFragment extends Fragment {

    private FloatingActionButton writeBtn;

    private ArrayList<Board> mArrayList = new ArrayList<>();
    private RecyclerAdapter mAdapter = new RecyclerAdapter();
    private RecyclerView recyclerView;

    DataService dataService = new DataService();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_board, container, false);
        // fragment findViewById 구하는 법, 프래그먼트는 액티비티가 아니라 getView를 통해서 findViewById를 구할 수 있음.
        recyclerView = (RecyclerView) view.findViewById(R.id.boardRecycle);
        // fragment context구하는 법 , 프래그먼트는 this나 getApplicationContext가 안먹히기 떄문에 getActivity를 통해서 구할 수 있음.
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setAdapter(mAdapter);

        Button likeBtn = (Button) view.findViewById(R.id.likeBtn);

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

        // 따로 담아야 함
        Button searchBtn = (Button) view.findViewById(R.id.search);
        searchBtn.setOnClickListener(v1 -> {
            Log.i("TAG", "onCreateView: search on");
            TextView et_search = view.findViewById(R.id.et_search);
            String search_str = et_search.getText().toString();
            BoardSearchDto boardSearchDto = new BoardSearchDto(search_str);
            dataService.boardAPI.searchBoard(boardSearchDto).enqueue(new Callback<ArrayList<BoardListResponseDto>>() {
                @Override
                public void onResponse(Call<ArrayList<BoardListResponseDto>> call, Response<ArrayList<BoardListResponseDto>> response) {
                    ArrayList<BoardListResponseDto> body = response.body();

                    mArrayList.clear();

                    for (int i = 0; i < body.size(); i++) {
                        String strDate = body.get(i).getModifiedDate();
                        LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
                        String modDate = localDateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd hh:mm"));
                        Board board = new Board(body.get(i).getId(), body.get(i).getTitle(), body.get(i).getContent(), modDate, body.get(i).getUser_id());
                        mArrayList.add(board);
                    }
                    Log.i("TAG", "onResponse: board = " + mArrayList);
                    mAdapter.addList(mArrayList);
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<BoardListResponseDto>> call, Throwable t) {

                }
            });

        });


        likeBtn.setOnClickListener(v -> {
            String sid = SharedPreference.getAttribute(getContext(), "id");
            Long id = Long.parseLong(sid);

            if (likeBtn.getText().equals("좋아요보기")) {
                Call<ArrayList<FindIdDto>> arrayListCall = dataService.boardAPI.selectLike(id);
                arrayListCall.enqueue(new Callback<ArrayList<FindIdDto>>() {
                    @Override
                    public void onResponse(Call<ArrayList<FindIdDto>> call, Response<ArrayList<FindIdDto>> response) {
                        ArrayList<FindIdDto> body = response.body();

                        mArrayList.clear();

                        for (int i = 0; i < body.size(); i++) {
                            String strDate = body.get(i).getModDate();
                            LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
                            String modDate = localDateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd hh:mm"));
                            Board board = new Board(body.get(i).getId(), body.get(i).getTitle(), body.get(i).getUser_id(), modDate, body.get(i).getContent());
                            mArrayList.add(board);
                        }
                        Log.i("TAG", "onResponse: board = " + mArrayList);
                        mAdapter.addList(mArrayList);
                        mAdapter.notifyDataSetChanged();
                        likeBtn.setText("전체게시글");
                    }

                    @Override
                    public void onFailure(Call<ArrayList<FindIdDto>> call, Throwable t) {

                    }
                });
            } else {
                Call<ArrayList<BoardListResponseDto>> arrayListCall = dataService.boardAPI.selectBoard();
                arrayListCall.enqueue(new Callback<ArrayList<BoardListResponseDto>>() {
                    @Override
                    public void onResponse(Call<ArrayList<BoardListResponseDto>> call, Response<ArrayList<BoardListResponseDto>> response) {
                        ArrayList<BoardListResponseDto> body = response.body();

                        mArrayList.clear();

                        for (int i = 0; i < response.body().size(); i++) {
                            String strDate = body.get(i).getModifiedDate();
                            LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
                            String modDate = localDateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd hh:mm"));
                            Board board = new Board(body.get(i).getId(), body.get(i).getTitle(), body.get(i).getUser_id(), modDate, body.get(i).getContent());
                            mArrayList.add(board);
                        }
                        Log.i("TAG", "onResponse: board = " + mArrayList);
                        mAdapter.addList(mArrayList);
                        mAdapter.notifyDataSetChanged();
                        likeBtn.setText("좋아요보기");
                    }

                    @Override
                    public void onFailure(Call<ArrayList<BoardListResponseDto>> call, Throwable t) {

                    }
                });
            }

        });

        return view;


    }

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

    @Override
    public void onStart() {

        super.onStart();
        Call<ArrayList<BoardListResponseDto>> arrayListCall = dataService.boardAPI.selectBoard();
        arrayListCall.enqueue(new Callback<ArrayList<BoardListResponseDto>>() {
            @Override
            public void onResponse(Call<ArrayList<BoardListResponseDto>> call, Response<ArrayList<BoardListResponseDto>> response) {
                ArrayList<BoardListResponseDto> body = response.body();

                mArrayList.clear();

                for (int i = 0; i < response.body().size(); i++) {
                    String strDate = body.get(i).getModifiedDate();
                    LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
                    String modDate = localDateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd hh:mm"));

                    Board board = new Board(body.get(i).getId(), body.get(i).getTitle(), body.get(i).getUser_id(), modDate, body.get(i).getContent());
                    mArrayList.add(board);
                }
                Log.i("TAG", "onResponse: board = " + mArrayList);
                mAdapter.addList(mArrayList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<BoardListResponseDto>> call, Throwable t) {

            }
        });

    }

    private void writeButton() {
        Intent intent = new Intent(getActivity(), BoardInsert.class);
        startActivity(intent);
    }
}
