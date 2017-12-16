package com.rotabuilder.dom.domain.shift;

import javax.jdo.annotations.IdentityType;

import com.rotabuilder.dom.domain.contract.Contract;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.solver.MovableShiftAssignmentSelectionFilter;
import com.rotabuilder.dom.domain.solver.ShiftAssignmentDifficultyComparator;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.rotabuilder.dom.domain.pattern.DayOfWeek;
import com.rotabuilder.dom.domain.pattern.WeekendDefinition;
import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import com.rotabuilder.dom.domain.solver.EmployeeStrengthComparator;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@PlanningEntity(difficultyComparatorClass = ShiftAssignmentDifficultyComparator.class,
        movableEntitySelectionFilter = MovableShiftAssignmentSelectionFilter.class)
@XStreamAlias("ShiftAssignment")
public class ShiftAssignment extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Shift shift;

    @javax.jdo.annotations.Column(allowsNull = "true")
    private Employee employee;

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @PlanningVariable(valueRangeProviderRefs = {"employeeRange"}, strengthComparatorClass = EmployeeStrengthComparator.class)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public ShiftDate getShiftDate() {
        return shift.getShiftDate();
    }

    public ShiftType getShiftType() {
        return shift.getShiftType();
    }

    public int getShiftDateDayIndex() {
        return shift.getShiftDate().getDayIndex();
    }

    public DayOfWeek getShiftDateDayOfWeek() {
        return shift.getShiftDate().getDayOfWeek();
    }

    public Contract getContract() {
        if (employee == null) {
            return null;
        }
        return employee.getContract();
    }

    public boolean isWeekend() {
        if (employee == null) {
            return false;
        }
        WeekendDefinition weekendDefinition = employee.getContract().getWeekendDefinition();
        DayOfWeek dayOfWeek = shift.getShiftDate().getDayOfWeek();
        return weekendDefinition.isWeekend(dayOfWeek);
    }

    public int getWeekendSundayIndex() {
        return shift.getShiftDate().getWeekendSundayIndex();
    }

    @Override
    public String toString() {
        return shift.toString();
    }

}
