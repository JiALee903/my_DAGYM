package org.techtown.dagym.ui.pt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.databinding.ActivityRequestListBinding;

public class RequestList extends AppCompatActivity {

    private ActivityRequestListBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());


    }
}
