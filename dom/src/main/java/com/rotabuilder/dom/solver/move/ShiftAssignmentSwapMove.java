package com.rotabuilder.dom.solver.move;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.shift.ShiftAssignment;

public class ShiftAssignmentSwapMove extends AbstractMove {

    private ShiftAssignment leftShiftAssignment;
    private ShiftAssignment rightShiftAssignment;

    public ShiftAssignmentSwapMove(ShiftAssignment leftShiftAssignment, ShiftAssignment rightShiftAssignment) {
        this.leftShiftAssignment = leftShiftAssignment;
        this.rightShiftAssignment = rightShiftAssignment;
    }

    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !ObjectUtils.equals(leftShiftAssignment.getEmployee(), rightShiftAssignment.getEmployee());
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new ShiftAssignmentSwapMove(rightShiftAssignment, leftShiftAssignment);
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector scoreDirector) {
        Employee oldLeftEmployee = leftShiftAssignment.getEmployee();
        Employee oldRightEmployee = rightShiftAssignment.getEmployee();
        EmployeeRosteringMoveHelper.moveEmployee(scoreDirector, leftShiftAssignment, oldRightEmployee);
        EmployeeRosteringMoveHelper.moveEmployee(scoreDirector, rightShiftAssignment, oldLeftEmployee);
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Arrays.asList(leftShiftAssignment, rightShiftAssignment);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Arrays.asList(leftShiftAssignment.getEmployee(), rightShiftAssignment.getEmployee());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ShiftAssignmentSwapMove) {
            ShiftAssignmentSwapMove other = (ShiftAssignmentSwapMove) o;
            return new EqualsBuilder()
                    .append(leftShiftAssignment, other.leftShiftAssignment)
                    .append(rightShiftAssignment, other.rightShiftAssignment)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(leftShiftAssignment)
                .append(rightShiftAssignment)
                .toHashCode();
    }

    public String toString() {
        return leftShiftAssignment + " {" + leftShiftAssignment.getEmployee() + "} <-> "
                + rightShiftAssignment + " {" + rightShiftAssignment.getEmployee() + "}";
    }

}
