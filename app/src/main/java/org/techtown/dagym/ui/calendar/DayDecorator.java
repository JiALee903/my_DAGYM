package org.techtown.dagym.ui.calendar;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

public class DayDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    public DayDecorator() {

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int date = calendar.get(Calendar.DAY_OF_WEEK);
        return date == Calendar.MONDAY || date == Calendar.TUESDAY || date == Calendar.WEDNESDAY || date == Calendar.THURSDAY || date == Calendar.FRIDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.BLACK));
    }
}
