package com.rotabuilder.dom.domain.employee;

import javax.jdo.annotations.IdentityType;

import com.rotabuilder.dom.domain.shift.ShiftDate;
import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@XStreamAlias("DayOffRequest")
public class DayOffRequest extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Employee employee;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private ShiftDate shiftDate;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private int weight;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ShiftDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(ShiftDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return shiftDate + "_OFF_" + employee;
    }

}
