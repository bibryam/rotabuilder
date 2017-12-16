package com.rotabuilder.dom.domain.shift;

import javax.jdo.annotations.IdentityType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.rotabuilder.dom.domain.solver.AbstractPersistable;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@XStreamAlias("Shift")
public class Shift extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private ShiftDate shiftDate;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private ShiftType shiftType;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private int requiredEmployeeSize;

    public ShiftDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(ShiftDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public int getRequiredEmployeeSize() {
        return requiredEmployeeSize;
    }

    public void setRequiredEmployeeSize(int requiredEmployeeSize) {
        this.requiredEmployeeSize = requiredEmployeeSize;
    }

    public String getLabel() {
        return shiftType.getLabel() + " of " + shiftDate.getLabel();
    }

    @Override
    public String toString() {
        return shiftDate + "/" + shiftType;
    }

}
