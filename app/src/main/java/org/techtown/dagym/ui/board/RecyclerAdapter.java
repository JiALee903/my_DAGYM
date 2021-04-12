package org.techtown.dagym.ui.board;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.R;
import org.techtown.dagym.entity.Board;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Board> mList;

    public Board getItem(int position) {
        return this.mList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        protected TextView id;
        protected TextView title;
        protected TextView user_id;
        protected TextView regDate;




        public ViewHolder(@NonNull View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.boardNo);
            this.title = (TextView) view.findViewById(R.id.boardTitle_recyc);
            this.user_id = (TextView) view.findViewById(R.id.boardWriter);
            this.regDate = (TextView) view.findViewById(R.id.boardDate);
        }

    }

    public RecyclerAdapter(ArrayList<Board> list) {
        this.mList = list;
    }

//    public RecyclerAdapter() {
//
//    }

    public void addItem(Board board) {
        mList.add(board);
        Log.i("TAG", "add: board = " + board);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.board_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
        // 글자 사이즈 지정
        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.user_id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.regDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);


        // 글자 중앙 정렬
        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.title.setGravity(Gravity.CENTER);
        viewholder.user_id.setGravity(Gravity.CENTER);
        viewholder.regDate.setGravity(Gravity.CENTER);

        // 저장된 값 꺼내서 setText해줌.
        Long l = mList.get(position).getId();
        String id = l.toString();
        viewholder.id.setText(id); //Integer.parseInt()
        viewholder.title.setText(mList.get(position).getTitle());
        viewholder.user_id.setText(mList.get(position).getUser_id());
        viewholder.regDate.setText(mList.get(position).getModDate());
    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }
}
