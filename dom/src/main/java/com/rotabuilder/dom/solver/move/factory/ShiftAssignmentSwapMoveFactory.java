package com.rotabuilder.dom.solver.move.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.rotabuilder.dom.domain.solver.EmployeeRoster;
import com.rotabuilder.dom.solver.move.ShiftAssignmentSwapMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;
import com.rotabuilder.dom.domain.shift.ShiftAssignment;
import com.rotabuilder.dom.domain.solver.MovableShiftAssignmentSelectionFilter;

public class ShiftAssignmentSwapMoveFactory implements MoveListFactory<EmployeeRoster> {

    private MovableShiftAssignmentSelectionFilter filter = new MovableShiftAssignmentSelectionFilter();

    public List<Move> createMoveList(EmployeeRoster employeeRoster) {
        // Filter out every immovable ShiftAssignment
        List<ShiftAssignment> shiftAssignmentList = new ArrayList<ShiftAssignment>(
                employeeRoster.getShiftAssignmentList());
        for (Iterator<ShiftAssignment> it = shiftAssignmentList.iterator(); it.hasNext(); ) {
            ShiftAssignment shiftAssignment = it.next();
            if (!filter.accept(employeeRoster, shiftAssignment)) {
                it.remove();
            }
        }
        List<Move> moveList = new ArrayList<Move>();
        for (ListIterator<ShiftAssignment> leftIt = shiftAssignmentList.listIterator(); leftIt.hasNext();) {
            ShiftAssignment leftShiftAssignment = leftIt.next();
            for (ListIterator<ShiftAssignment> rightIt = shiftAssignmentList.listIterator(leftIt.nextIndex()); rightIt.hasNext();) {
                ShiftAssignment rightShiftAssignment = rightIt.next();
                moveList.add(new ShiftAssignmentSwapMove(leftShiftAssignment, rightShiftAssignment));
            }
        }
        return moveList;
    }

}
