package org.techtown.dagym.ui.pt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import org.techtown.dagym.R;
import org.techtown.dagym.databinding.ActivityPersonaltBinding;
import org.techtown.dagym.databinding.ActivitySearchfBinding;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchfBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

    }
}
