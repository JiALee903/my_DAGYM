package org.techtown.dagym.ui.pt;

import android.content.Context;
import android.content.Intent;
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

public class PTActivity extends AppCompatActivity {

    private ActivityPersonaltBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = b.inflate(getLayoutInflater());
        setContentView(b.getRoot());

//        final GradientDrawable cdrawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.ic_chat);
//        final GradientDrawable fdrawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.ic_friend);

        b.friendBtn.setOnClickListener(v -> {
            b.friend.setVisibility(View.VISIBLE);
            b.chat.setVisibility(View.GONE);

        });

        b.chatBtn.setOnClickListener(v -> {
            b.friend.setVisibility(View.GONE);
            b.chat.setVisibility(View.VISIBLE);
        });

        b.addFriend.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(intent);
        });
    }
}
