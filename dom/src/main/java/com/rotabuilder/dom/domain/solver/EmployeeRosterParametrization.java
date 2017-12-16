package com.rotabuilder.dom.domain.solver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.rotabuilder.dom.domain.shift.ShiftDate;

@XStreamAlias("EmployeeRosterParametrization")
public class EmployeeRosterParametrization extends AbstractPersistable {

    private ShiftDate firstShiftDate;
    private ShiftDate lastShiftDate;

    private ShiftDate planningWindowStart;

    public ShiftDate getFirstShiftDate() {
        return firstShiftDate;
    }

    public void setFirstShiftDate(ShiftDate firstShiftDate) {
        this.firstShiftDate = firstShiftDate;
    }

    public ShiftDate getLastShiftDate() {
        return lastShiftDate;
    }

    public void setLastShiftDate(ShiftDate lastShiftDate) {
        this.lastShiftDate = lastShiftDate;
    }

    public int getFirstShiftDateDayIndex() {
        return firstShiftDate.getDayIndex();
    }

    public int getLastShiftDateDayIndex() {
        return lastShiftDate.getDayIndex();
    }

    public ShiftDate getPlanningWindowStart() {
        return planningWindowStart;
    }

    public void setPlanningWindowStart(ShiftDate planningWindowStart) {
        this.planningWindowStart = planningWindowStart;
    }

    // ************************************************************************
    // Worker methods
    // ************************************************************************

    public boolean isInPlanningWindow(ShiftDate shiftDate) {
        return planningWindowStart.getDayIndex() <= shiftDate.getDayIndex();
    }

    @Override
    public String toString() {
        return firstShiftDate + " - " + lastShiftDate;
    }

}
