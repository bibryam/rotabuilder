package com.rotabuilder.fixture.dom;

import com.rotabuilder.dom.domain.pattern.DayOfWeek;
import com.rotabuilder.dom.domain.shift.ShiftDate;
import com.rotabuilder.dom.domain.shift.ShiftRepository;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class ShiftDateCreate extends FixtureScript {

    private String dateString;
    private int dayIndex;
    private DayOfWeek dayOfWeek;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setShiftDate(ShiftDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    //region > nurse (output)
    private ShiftDate shiftDate;

    public ShiftDate getShiftDate() {
        return shiftDate;
    }

    @Override
    protected void execute(final ExecutionContext ec) {

        String dateString = checkParam("dateString", ec, String.class);
        Integer dayIndex = checkParam("dayIndex", ec, Integer.class);
        DayOfWeek dayOfWeek = checkParam("dayOfWeek", ec, DayOfWeek.class);

        this.shiftDate = wrap(shiftRepository).createShiftDate(dateString, dayIndex, dayOfWeek);

        // also make available to UI
        ec.addResult(this, shiftDate);
    }

    @javax.inject.Inject
    private ShiftRepository shiftRepository;

}
