package org.techtown.dagym.ui.inbody;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.R;
import org.techtown.dagym.entity.dto.InBodyValue;

import java.util.ArrayList;

public class InbodyAdapter extends RecyclerView.Adapter<InbodyAdapter.ViewHolder> {
    ArrayList<InBodyValue> list = new ArrayList<>();
    private InbodyAdapter.OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbody_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.inbodyValue.setText(list.get(position).getValue());
        holder.dateInbody.setText(list.get(position).getInBody_date());
        holder.inbodyDelete.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, position);
        });
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView dateInbody;
        protected TextView inbodyValue;
        protected Button inbodyDelete;
        public ViewHolder(@NonNull View view) {
            super(view);
            this.dateInbody = (TextView) view.findViewById(R.id.dateInbody);
            this.inbodyValue = (TextView) view.findViewById(R.id.inbodyValue);
            this.inbodyDelete = (Button) view.findViewById(R.id.inbody_delete);
        }
    }

    public InbodyAdapter() {

    }

    public void addItem(ArrayList<InBodyValue> list) {
        this.list = list;
    }

    public void onClick(InbodyAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public InBodyValue getItem(int position) {
        return this.list.get(position);
    }

}
