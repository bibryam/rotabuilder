package com.rotabuilder.dom.domain.pattern;

import javax.jdo.annotations.InheritanceStrategy;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.rotabuilder.dom.domain.shift.ShiftType;

@javax.jdo.annotations.PersistenceCapable
@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)

@XStreamAlias("WorkBeforeFreeSequencePattern")
public class WorkBeforeFreeSequencePattern extends Pattern {

    @javax.jdo.annotations.Column(allowsNull = "true")
    private DayOfWeek workDayOfWeek; // null means any

    @javax.jdo.annotations.Column(allowsNull = "true")
    private ShiftType workShiftType; // null means any

    @javax.jdo.annotations.Column(allowsNull = "false")
    private int freeDayLength;

    public DayOfWeek getWorkDayOfWeek() {
        return workDayOfWeek;
    }

    public void setWorkDayOfWeek(DayOfWeek workDayOfWeek) {
        this.workDayOfWeek = workDayOfWeek;
    }

    public ShiftType getWorkShiftType() {
        return workShiftType;
    }

    public void setWorkShiftType(ShiftType workShiftType) {
        this.workShiftType = workShiftType;
    }

    public int getFreeDayLength() {
        return freeDayLength;
    }

    public void setFreeDayLength(int freeDayLength) {
        this.freeDayLength = freeDayLength;
    }

    @Override
    public String toString() {
        return "Work " + workShiftType + " on " + workDayOfWeek + " followed by " + freeDayLength + " free days";
    }

}
