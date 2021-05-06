package org.techtown.dagym.ui.pt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.DataService;
import org.techtown.dagym.R;
import org.techtown.dagym.entity.dto.AndPTUserSearchDto;

import java.util.ArrayList;

public class FindMemberAdapter extends RecyclerView.Adapter<FindMemberAdapter.ViewHolder> {

    ArrayList<AndPTUserSearchDto> list = new ArrayList<>();
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

        public ViewHolder(@NonNull View view) {
            super(view);
            this.applicantName = (TextView) view.findViewById(R.id.friendName);
            this.applicantID = (TextView) view.findViewById(R.id.friendID);
            this.applicantOKBtn = (Button) view.findViewById(R.id.applicantOKBtn);
            this.applicantNoBtn = (Button) view.findViewById(R.id.applicantNoBtn);
        }
    }

    public void onClick(FindMemberAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position, String apply_if);
    }

    public void addList(ArrayList<AndPTUserSearchDto> list) {
        this.list = list;
    }

    public AndPTUserSearchDto getItem(int position) {
        return this.list.get(position);
    }


}
