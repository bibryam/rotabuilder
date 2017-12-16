package com.rotabuilder.dom.domain.pattern;

import javax.jdo.annotations.InheritanceStrategy;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.rotabuilder.dom.domain.shift.ShiftType;

@javax.jdo.annotations.PersistenceCapable
@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)

@XStreamAlias("ShiftType2DaysPattern")
public class ShiftType2DaysPattern extends Pattern {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private ShiftType dayIndex0ShiftType;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private ShiftType dayIndex1ShiftType;

    public ShiftType getDayIndex0ShiftType() {
        return dayIndex0ShiftType;
    }

    public void setDayIndex0ShiftType(ShiftType dayIndex0ShiftType) {
        this.dayIndex0ShiftType = dayIndex0ShiftType;
    }

    public ShiftType getDayIndex1ShiftType() {
        return dayIndex1ShiftType;
    }

    public void setDayIndex1ShiftType(ShiftType dayIndex1ShiftType) {
        this.dayIndex1ShiftType = dayIndex1ShiftType;
    }

    @Override
    public String toString() {
        return "Work pattern: " + dayIndex0ShiftType + ", " + dayIndex1ShiftType;
    }

}
