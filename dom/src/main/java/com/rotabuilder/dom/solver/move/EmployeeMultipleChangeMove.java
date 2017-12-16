package com.rotabuilder.dom.solver.move;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.shift.ShiftAssignment;

public class EmployeeMultipleChangeMove extends AbstractMove {

    private Employee fromEmployee;
    private List<ShiftAssignment> shiftAssignmentList;
    private Employee toEmployee;

    public EmployeeMultipleChangeMove(Employee fromEmployee, List<ShiftAssignment> shiftAssignmentList, Employee toEmployee) {
        this.fromEmployee = fromEmployee;
        this.shiftAssignmentList = shiftAssignmentList;
        this.toEmployee = toEmployee;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !ObjectUtils.equals(fromEmployee, toEmployee);
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new EmployeeMultipleChangeMove(toEmployee, shiftAssignmentList, fromEmployee);
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector scoreDirector) {
        for (ShiftAssignment shiftAssignment : shiftAssignmentList) {
            if (!shiftAssignment.getEmployee().equals(fromEmployee)) {
                throw new IllegalStateException("The shiftAssignment (" + shiftAssignment + ") should have the same employee ("
                        + fromEmployee + ") as the fromEmployee (" + fromEmployee + ").");
            }
            EmployeeRosteringMoveHelper.moveEmployee(scoreDirector, shiftAssignment, toEmployee);
        }
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(shiftAssignmentList);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Arrays.asList(fromEmployee, toEmployee);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof EmployeeMultipleChangeMove) {
            EmployeeMultipleChangeMove other = (EmployeeMultipleChangeMove) o;
            return new EqualsBuilder()
                    .append(fromEmployee, other.fromEmployee)
                    .append(shiftAssignmentList, other.shiftAssignmentList)
                    .append(toEmployee, other.toEmployee)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(fromEmployee)
                .append(shiftAssignmentList)
                .append(toEmployee)
                .toHashCode();
    }

    public String toString() {
        return shiftAssignmentList + " {? -> " + toEmployee + "}";
    }

}
