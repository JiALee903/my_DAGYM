package org.techtown.dagym.ui.pt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.R;
import org.techtown.dagym.entity.dto.AndPTUserSearchDto;

import java.util.ArrayList;

// 친구목록 리사이클러뷰 어뎁터
public class PTActivityAdapter extends RecyclerView.Adapter<PTActivityAdapter.ViewHolder> {
    ArrayList<AndPTUserSearchDto> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendrc_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.friendName.setText(list.get(position).getUser_name());
        holder.friendId.setText(list.get(position).getUser_id());
    }

    @Override
    public int getItemCount()  {
        return (list != null ? list.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView friendName;
        protected TextView friendId;
        public ViewHolder(@NonNull View view) {
            super(view);
            this.friendName = (TextView) view.findViewById(R.id.dateInbody);
            this.friendId = (TextView) view.findViewById(R.id.inbodyValue);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(v, pos) ;
                        }
                    }
                }
            });
        }
    }

    public void onClick(PTActivityAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void addList(ArrayList<AndPTUserSearchDto> list) {
        this.list = list;
    }

    public AndPTUserSearchDto getItem(int position) {
        return this.list.get(position);
    }
}
