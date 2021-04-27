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

    private ArrayList<Board> mList = new ArrayList<>();

    public Board getItem(int position) {
        return this.mList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        protected TextView title;
        protected TextView user_id;
        protected TextView regDate;
        protected TextView content;



        public ViewHolder(@NonNull View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.boardTitle_recyc);
            this.user_id = (TextView) view.findViewById(R.id.boardWriter);
            this.regDate = (TextView) view.findViewById(R.id.boardDate);
            this.content = (TextView) view.findViewById(R.id.boardContent);
        }
    }

    public RecyclerAdapter() {
        Log.i("TAG", "RecyclerAdapter: here is adapter");
    }

    public void addList(ArrayList<Board> list) {
        this.mList = list;
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
        viewholder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.user_id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.regDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.content.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);


        // 글자 중앙 정렬
        viewholder.title.setGravity(Gravity.CENTER);
        viewholder.user_id.setGravity(Gravity.CENTER);
        viewholder.regDate.setGravity(Gravity.CENTER);

        // 저장된 값 꺼내서 setText해줌.
        viewholder.title.setText(mList.get(position).getTitle());
        viewholder.user_id.setText(mList.get(position).getUser_id());
        viewholder.regDate.setText(mList.get(position).getModDate());
        viewholder.content.setText(mList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }
}
