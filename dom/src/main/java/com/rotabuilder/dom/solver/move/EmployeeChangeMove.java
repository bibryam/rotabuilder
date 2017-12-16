package com.rotabuilder.dom.solver.move;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.shift.ShiftAssignment;

public class EmployeeChangeMove extends AbstractMove {

    private ShiftAssignment shiftAssignment;
    private Employee toEmployee;

    public EmployeeChangeMove(ShiftAssignment shiftAssignment, Employee toEmployee) {
        this.shiftAssignment = shiftAssignment;
        this.toEmployee = toEmployee;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !ObjectUtils.equals(shiftAssignment.getEmployee(), toEmployee);
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new EmployeeChangeMove(shiftAssignment, shiftAssignment.getEmployee());
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector scoreDirector) {
        EmployeeRosteringMoveHelper.moveEmployee(scoreDirector, shiftAssignment, toEmployee);
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(shiftAssignment);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(toEmployee);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof EmployeeChangeMove) {
            EmployeeChangeMove other = (EmployeeChangeMove) o;
            return new EqualsBuilder()
                    .append(shiftAssignment, other.shiftAssignment)
                    .append(toEmployee, other.toEmployee)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(shiftAssignment)
                .append(toEmployee)
                .toHashCode();
    }

    public String toString() {
        return shiftAssignment + " {" + shiftAssignment.getEmployee() + " -> " + toEmployee + "}";
    }

}
