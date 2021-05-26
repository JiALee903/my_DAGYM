package org.techtown.dagym.ui.pt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.techtown.dagym.R;
import org.techtown.dagym.entity.dto.AndPTUserApplyMemberDto;

import java.util.ArrayList;

// 신청목록 리스트 리사이클러뷰의 어뎁터
public class FindMemberAdapter extends RecyclerView.Adapter<FindMemberAdapter.ViewHolder> {

    ArrayList<AndPTUserApplyMemberDto> list = new ArrayList<>();
    private FindMemberAdapter.OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.applicantOKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position, "수락");
            }
        });

        holder.applicantNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position, "거절");
            }
        });

        holder.applicantID.setText(list.get(position).getUser_id());
        holder.applicantName.setText(list.get(position).getUser_name());
        holder.start_end_date.setText("(" +
                list.get(position).getStart_date() +
                "~" +
                list.get(position).getEnd_date()
                +")");
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView applicantName;
        protected TextView applicantID;
        protected Button applicantOKBtn;
        protected Button applicantNoBtn;
        protected TextView start_end_date;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.applicantName = (TextView) view.findViewById(R.id.dateInbody);
            this.applicantID = (TextView) view.findViewById(R.id.inbodyValue);
            this.applicantOKBtn = (Button) view.findViewById(R.id.applicantOKBtn);
            this.applicantNoBtn = (Button) view.findViewById(R.id.applicantNoBtn);
            this.start_end_date = (TextView) view.findViewById(R.id.start_end_date);
        }
    }

    public void onClick(FindMemberAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position, String apply_if);
    }

    public void addList(ArrayList<AndPTUserApplyMemberDto> list) {
        this.list = list;
    }

    public AndPTUserApplyMemberDto getItem(int position) {
        return this.list.get(position);
    }


}
