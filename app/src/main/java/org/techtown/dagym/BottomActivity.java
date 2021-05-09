package org.techtown.dagym;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.dagym.ui.pt.BottomSheetTest;

public class BottomActivity extends AppCompatActivity {
    TextView bottomOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_main_test);

        bottomOpen = findViewById(R.id.bottomTest);

        bottomOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetTest bst = new BottomSheetTest();
                bst.show(getSupportFragmentManager(), "");
            }
        });
    }
}
