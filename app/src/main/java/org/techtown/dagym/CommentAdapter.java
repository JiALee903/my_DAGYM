package org.techtown.dagym;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.entity.dto.CommentDto;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    ArrayList<CommentDto> mList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView comments;
        protected TextView user_id;
        protected TextView modDate;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.comments = (TextView) view.findViewById(R.id.commnet);
            this.user_id = (TextView) view.findViewById(R.id.comment_nick);
            this.modDate = (TextView) view.findViewById(R.id.comment_mod);
        }
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
        holder.comments.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.user_id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.modDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        holder.comments.setText(mList.get(position).getComments());
        holder.user_id.setText(mList.get(position).getUser_id());
        holder.modDate.setText(mList.get(position).getModDate());
    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }


}
