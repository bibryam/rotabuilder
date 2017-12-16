package com.rotabuilder.dom.solver.move;

import org.optaplanner.core.impl.score.director.ScoreDirector;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.shift.ShiftAssignment;

public class EmployeeRosteringMoveHelper {

    public static void moveEmployee(ScoreDirector scoreDirector, ShiftAssignment shiftAssignment, Employee toEmployee) {
        scoreDirector.beforeVariableChanged(shiftAssignment, "employee");
        shiftAssignment.setEmployee(toEmployee);
        scoreDirector.afterVariableChanged(shiftAssignment, "employee");
    }

    private EmployeeRosteringMoveHelper() {
    }

}
