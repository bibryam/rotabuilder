package com.rotabuilder.dom.solver.move.factory;

import java.util.ArrayList;
import java.util.List;

import com.rotabuilder.dom.solver.move.EmployeeChangeMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.solver.EmployeeRoster;
import com.rotabuilder.dom.domain.shift.ShiftAssignment;
import com.rotabuilder.dom.domain.solver.MovableShiftAssignmentSelectionFilter;

public class EmployeeChangeMoveFactory implements MoveListFactory<EmployeeRoster> {

    private MovableShiftAssignmentSelectionFilter filter = new MovableShiftAssignmentSelectionFilter();

    public List<Move> createMoveList(EmployeeRoster employeeRoster) {
        List<Move> moveList = new ArrayList<Move>();
        List<Employee> employeeList = employeeRoster.getEmployeeList();
        for (ShiftAssignment shiftAssignment : employeeRoster.getShiftAssignmentList()) {
            if (filter.accept(employeeRoster, shiftAssignment)) {
                for (Employee employee : employeeList) {
                    moveList.add(new EmployeeChangeMove(shiftAssignment, employee));
                }
            }
        }
        return moveList;
    }

}
