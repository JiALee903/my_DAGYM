package org.techtown.dagym.ui.pt;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.techtown.dagym.R;

import java.util.Calendar;

public class BottomSheetTest extends BottomSheetDialogFragment {

    private TextView tv, vt;
    private DatePickerDialog.OnDateSetListener dsl, lsd;

    // public BottomSheetTest() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout_test, container, false);

        tv = (TextView) v.findViewById(R.id.startText);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_DeviceDefault_Dialog, dsl, year, month, day);

                dialog.show();
            }
        });

        dsl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                tv.setText(year + "/" + month + "/" + day);
            }
        };

        vt = (TextView) v.findViewById(R.id.finishText);

        vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dia = new DatePickerDialog(getContext(),
                        android.R.style.Theme_DeviceDefault_Dialog, lsd, year, month, day);

                dia.show();
            }
        });

        lsd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                vt.setText(year + "/" + month + "/" + day);
            }
        };

        return v;
    }
}
