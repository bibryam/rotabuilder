package com.rotabuilder.dom.domain.solver;

import java.io.Serializable;
import java.util.Comparator;

import com.rotabuilder.dom.domain.employee.Employee;
import org.apache.commons.lang3.builder.CompareToBuilder;

public class EmployeeStrengthComparator implements Comparator<Employee>, Serializable {

    public int compare(Employee a, Employee b) {
        // TODO refactor to DifficultyWeightFactory and use getContract().getContractLineList()
        // to sum maximumValue and minimumValue etc
        return new CompareToBuilder()
                .append(b.getWeekendLength(), a.getWeekendLength()) // Descending
//                .append(a.getId(), b.getId())
                .toComparison();
    }

}
