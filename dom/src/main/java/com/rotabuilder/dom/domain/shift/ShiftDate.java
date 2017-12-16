package com.rotabuilder.dom.domain.shift;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.jdo.annotations.IdentityType;

import com.rotabuilder.dom.domain.pattern.DayOfWeek;
import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.title.TitleService;
import org.isisaddons.wicket.fullcalendar2.cpt.applib.CalendarEvent;
import org.isisaddons.wicket.fullcalendar2.cpt.applib.CalendarEventable;
import org.joda.time.DateTime;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@DomainObject(
        autoCompleteRepository = ShiftRepository.class,
        autoCompleteAction = "autoCompleteShiftDate")
@XStreamAlias("ShiftDate")
public class ShiftDate extends AbstractPersistable implements CalendarEventable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private int dayIndex;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String dateString;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private DayOfWeek dayOfWeek;

    @javax.jdo.annotations.Column(allowsNull = "true")
    private List<Shift> shiftList;

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    public String determineNextDateString() {
        TimeZone LOCAL_TIMEZONE = TimeZone.getTimeZone("GMT");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(LOCAL_TIMEZONE);
        calendar.clear();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setCalendar(calendar);
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalStateException("Could not parse shiftDate (" + this
                    + ")'s dateString (" + dateString + ").");
        }
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return dateFormat.format(calendar.getTime());
    }

    public int getWeekendSundayIndex() {
        switch (dayOfWeek) {
            case MONDAY:
                return dayIndex - 1;
            case TUESDAY:
                return dayIndex - 2;
            case WEDNESDAY:
                return dayIndex - 3;
            case THURSDAY:
                return dayIndex + 3;
            case FRIDAY:
                return dayIndex + 2;
            case SATURDAY:
                return dayIndex + 1;
            case SUNDAY:
                return dayIndex;
            default:
                throw new IllegalArgumentException("The dayOfWeek (" + dayOfWeek + ") is not valid.");
        }
    }

    public String getLabel() {
        return dayOfWeek.getLabel() + " " + dateString.substring(5).replaceAll("\\-", "/");
    }

    @Override
    public String toString() {
        return dateString;
    }

    @Programmatic
    @Override
    public String getCalendarName() {
        return "Shifts";
    }

    @Programmatic
    @Override
    public CalendarEvent toCalendarEvent() {
        return new CalendarEvent(DateTime.now(), "Shifts", titleService.titleOf(this));
    }

    @javax.inject.Inject
    TitleService titleService;
}
