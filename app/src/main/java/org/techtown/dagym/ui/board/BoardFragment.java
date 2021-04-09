package org.techtown.dagym.ui.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.techtown.dagym.BoardInsert;
import org.techtown.dagym.DataService;
import org.techtown.dagym.R;

public class BoardFragment extends Fragment {

    DataService dataService;

    private Button writeBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_board, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            writeBtn = view.findViewById(R.id.boardWriteBtn);
            writeBtn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.boardWriteBtn:
                    writeButton();
            }
        }
    };

    private void writeButton() {
            Intent intent = new Intent(getActivity(), BoardInsert.class);
            startActivity(intent);
        }
}
