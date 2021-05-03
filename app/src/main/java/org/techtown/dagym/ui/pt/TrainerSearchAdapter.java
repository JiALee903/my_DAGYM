package org.techtown.dagym.ui.pt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.dagym.R;
import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.AndPTUserSearchDto;
import org.techtown.dagym.entity.dto.CommentDto;

import java.util.ArrayList;

public class TrainerSearchAdapter  extends RecyclerView.Adapter<TrainerSearchAdapter.ViewHolder>{
    ArrayList<AndPTUserSearchDto> list = new ArrayList<>();

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 아이템 만들어 넣기
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainser_search_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.searchId.setText(list.get(position).getUser_id());
        holder.searchNick.setText(list.get(position).getUser_name());
        holder.searchEmail.setText(list.get(position).getUser_email());

    }

    public AndPTUserSearchDto getItem(int position) {
        return this.list.get(position);
    }

    public void addList(ArrayList<AndPTUserSearchDto> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView searchId;
        protected TextView searchNick;
        protected TextView searchEmail;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.searchId = (TextView) view.findViewById(R.id.search_id);
            this.searchNick = (TextView) view.findViewById(R.id.search_nick);
            this.searchEmail = (TextView) view.findViewById(R.id.search_email);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
