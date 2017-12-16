package com.rotabuilder.dom.domain.solver;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;
import com.rotabuilder.dom.domain.shift.Shift;
import com.rotabuilder.dom.domain.shift.ShiftAssignment;

public class ShiftAssignmentDifficultyComparator implements Comparator<ShiftAssignment>, Serializable {

    public int compare(ShiftAssignment a, ShiftAssignment b) {
        Shift aShift = a.getShift();
        Shift bShift = b.getShift();
        return new CompareToBuilder()
                    .append(bShift.getShiftDate(), aShift.getShiftDate()) // Descending
                    .append(bShift.getShiftType(), aShift.getShiftType()) // Descending
                    // For construction heuristics, scheduling the shifts in sequence is better
                    .append(aShift.getRequiredEmployeeSize(), bShift.getRequiredEmployeeSize())
                    .toComparison();
    }

}
