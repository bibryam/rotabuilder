package com.rotabuilder.fixture.dom;

import com.rotabuilder.dom.domain.shift.Shift;
import com.rotabuilder.dom.domain.shift.ShiftDate;
import com.rotabuilder.dom.domain.shift.ShiftRepository;
import com.rotabuilder.dom.domain.shift.ShiftType;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class ShiftCreate extends FixtureScript {

    private ShiftType shiftType;
    private ShiftDate shiftDate;
    private Integer requiredEmployeeSize;
    private Shift shift;

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public ShiftDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(ShiftDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public Integer getRequiredEmployeeSize() {
        return requiredEmployeeSize;
    }

    public void setRequiredEmployeeSize(Integer requiredEmployeeSize) {
        this.requiredEmployeeSize = requiredEmployeeSize;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    protected void execute(final ExecutionContext ec) {

        ShiftType shiftType = checkParam("shiftType", ec, ShiftType.class);
        ShiftDate shiftDate = checkParam("shiftDate", ec, ShiftDate.class);
        Integer requiredEmployeeSize = checkParam("requiredEmployeeSize", ec, Integer.class);

        this.shift = wrap(shiftRepository).createShift(shiftType, shiftDate, requiredEmployeeSize);

        // also make available to UI
        ec.addResult(this, shiftDate);
    }

    @javax.inject.Inject
    private ShiftRepository shiftRepository;

}
