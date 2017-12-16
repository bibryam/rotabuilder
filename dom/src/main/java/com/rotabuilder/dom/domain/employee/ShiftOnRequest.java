package com.rotabuilder.dom.domain.employee;

import javax.jdo.annotations.IdentityType;

import com.rotabuilder.dom.domain.shift.Shift;
import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@XStreamAlias("ShiftOnRequest")
public class ShiftOnRequest extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Employee employee;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Shift shift;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private int weight;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return shift + "_ON_" + employee;
    }

}
