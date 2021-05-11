package org.techtown.dagym.ui.board;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.R;
import org.techtown.dagym.entity.dto.CommentDto;

import java.util.ArrayList;

// 댓글 리사이클러뷰 어뎁터
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    ArrayList<CommentDto> mList = new ArrayList<>();
    private String user_id;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView comments;
        protected TextView user_id;
        protected TextView modDate;
        protected Button delete;


        public ViewHolder(@NonNull View view) {
            super(view);
            this.comments = (TextView) view.findViewById(R.id.search_nick);
            this.user_id = (TextView) view.findViewById(R.id.search_id);
            this.modDate = (TextView) view.findViewById(R.id.search_email);

            this.delete = (Button) view.findViewById(R.id.comment_delete);
        }
    }

//    public TextView getDelete() {
//        return delete;
//
//    }

    public CommentDto getItem(int position) {
        Log.i("TAG", "getItem: mList = " + mList.get(position).getComments());
        return this.mList.get(position);
    }

//    public CommentAdapter(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

    public void onClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CommentAdapter() {

    }

    public void getUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void addList(ArrayList<CommentDto> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }





    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick: 공듀 = " + position);
                onItemClickListener.onItemClick(view, position);
            }
        });
        if(!mList.get(position).getUser_id().equals(user_id)) {
            Log.i("TAG", "onBindViewHolder: ??? + " + mList.get(position).getUser_id() + ", user_id = " + user_id);
            holder.delete.setVisibility(View.GONE);
        } else {
            holder.delete.setVisibility(View.VISIBLE);
        }
        holder.comments.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.user_id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.modDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        holder.comments.setText(mList.get(position).getComments());
        holder.user_id.setText(mList.get(position).getUser_id());
        holder.modDate.setText(mList.get(position).getModDate());
        Log.i("TAG", "onBindViewHolder: holder comment = " + mList.get(position).getComments());
    }



    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }


}
