package com.rotabuilder.fixture.scenarios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.google.common.collect.Lists;

import com.rotabuilder.fixture.dom.ShiftDateCreate;
import com.rotabuilder.fixture.dom.ShiftDateTearDown;
import com.rotabuilder.fixture.dom.ShiftTearDown;
import org.apache.isis.applib.annotation.Programmatic;

import com.rotabuilder.dom.domain.pattern.DayOfWeek;
import com.rotabuilder.dom.domain.shift.Shift;
import com.rotabuilder.dom.domain.shift.ShiftDate;
import com.rotabuilder.dom.domain.shift.ShiftMenu;
import com.rotabuilder.dom.domain.shift.ShiftType;
import com.rotabuilder.fixture.dom.ShiftCreate;

public class RecreateShiftDates extends MyFixtureScript {

    public RecreateShiftDates() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    private final List<ShiftDate> shiftDates = Lists.newArrayList();
    private final List<Shift> shifts = Lists.newArrayList();

    @Programmatic
    public List<ShiftDate> getShiftDates() {
        return shiftDates;
    }

    @Programmatic
    public List<Shift> getShifts() {
        return shifts;
    }

    @Override
    protected void doExecute(final ExecutionContext ec) {

        ec.executeChild(this, new ShiftDateTearDown());
        ec.executeChild(this, new ShiftTearDown());

        List<ShiftType> shiftTypes = shiftMenu.listShiftTypes();
        ShiftType early = shiftTypes.get(0);

        List<ShiftDate> shiftDates = generateShiftDateList("2010-01-01", "2010-02-01");
        for (ShiftDate shiftDate : shiftDates) {
            ShiftDateCreate shiftDateCreate = new ShiftDateCreate();
            shiftDateCreate.setDateString(shiftDate.getDateString());
            shiftDateCreate.setDayIndex(shiftDate.getDayIndex());
            shiftDateCreate.setDayOfWeek(shiftDate.getDayOfWeek());

            ec.executeChild(this, shiftDateCreate.getDateString(), shiftDateCreate);
            this.shiftDates.add(shiftDateCreate.getShiftDate());


            ShiftCreate shiftCreate = new ShiftCreate();
            shiftCreate.setShiftDate(shiftDateCreate.getShiftDate());
            shiftCreate.setRequiredEmployeeSize(1);
            shiftCreate.setShiftType(early);

            ec.executeChild(this, shiftDateCreate.getDateString(), shiftCreate);
            this.shifts.add(shiftCreate.getShift());
        }
    }

    private List<ShiftDate> generateShiftDateList(String start, String stop) {
        TimeZone LOCAL_TIMEZONE = TimeZone.getTimeZone("GMT");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(LOCAL_TIMEZONE);
        calendar.clear();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setCalendar(calendar);
        Date startDate;
        try {
            startDate = dateFormat.parse(start);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid startDate (" + start + ").", e);
        }
        calendar.setTime(startDate);
        int startDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int startYear = calendar.get(Calendar.YEAR);
        Date endDate;
        try {
            endDate = dateFormat.parse(stop);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid endDate (" + stop + ").", e);
        }
        calendar.setTime(endDate);
        int endDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int endYear = calendar.get(Calendar.YEAR);
        int maxDayIndex = endDayOfYear - startDayOfYear;
        if (startYear > endYear) {
            throw new IllegalStateException("The startYear (" + startYear + " must be before endYear (" + endYear + ").");
        }
        if (startYear < endYear) {
            int tmpYear = startYear;
            calendar.setTime(startDate);
            while (tmpYear < endYear) {
                maxDayIndex += calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
                calendar.add(Calendar.YEAR, 1);
                tmpYear++;
            }
        }
        int shiftDateSize = maxDayIndex + 1;
        List<ShiftDate> shiftDateList = new ArrayList<ShiftDate>(shiftDateSize);
        long id = 0L;
        int dayIndex = 0;
        calendar.setTime(startDate);
        for (int i = 0; i < shiftDateSize; i++) {
            ShiftDate shiftDate = new ShiftDate();
            shiftDate.setDayIndex(dayIndex);
            String dateString = dateFormat.format(calendar.getTime());
            shiftDate.setDateString(dateString);
            shiftDate.setDayOfWeek(DayOfWeek.valueOfCalendar(calendar.get(Calendar.DAY_OF_WEEK)));
            shiftDate.setShiftList(new ArrayList<Shift>());
            shiftDateList.add(shiftDate);
            id++;
            dayIndex++;
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return shiftDateList;
    }

    @javax.inject.Inject
    private ShiftMenu shiftMenu;
}
