package com.rotabuilder.dom.domain.solver;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import com.rotabuilder.dom.domain.shift.ShiftAssignment;
import com.rotabuilder.dom.domain.shift.ShiftDate;

public class MovableShiftAssignmentSelectionFilter implements SelectionFilter<ShiftAssignment> {

    public boolean accept(ScoreDirector scoreDirector, ShiftAssignment shiftAssignment) {
        EmployeeRoster employeeRoster = (EmployeeRoster) scoreDirector.getWorkingSolution();
        return accept(employeeRoster, shiftAssignment);
    }

    public boolean accept(EmployeeRoster employeeRoster, ShiftAssignment shiftAssignment) {
        ShiftDate shiftDate = shiftAssignment.getShift().getShiftDate();
        return employeeRoster.getEmployeeRosterParametrization().isInPlanningWindow(shiftDate);
    }

}
