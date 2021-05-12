package org.techtown.dagym.ui.calendar;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.techtown.dagym.R;

public class CalendarSelector implements DayViewDecorator {

    private final Drawable drawable;

    public CalendarSelector(CalendarFragment context) {
        drawable = context.getResources().getDrawable(R.drawable.calendar_selector);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
