package org.techtown.dagym;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.databinding.BoardDetailBinding;

public class BoardDetail extends AppCompatActivity {
    private BoardDetailBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

    }
}
