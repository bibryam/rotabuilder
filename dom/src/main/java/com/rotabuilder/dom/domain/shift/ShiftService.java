package com.rotabuilder.dom.domain.shift;

import java.util.ArrayList;
import java.util.List;

import com.rotabuilder.dom.domain.contract.BooleanContractLine;
import com.rotabuilder.dom.domain.contract.Contract;
import com.rotabuilder.dom.domain.contract.MinMaxContractLine;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.employee.EmployeeRepository;
import com.rotabuilder.dom.domain.employee.Skill;
import com.rotabuilder.dom.domain.contract.ContractLine;
import com.rotabuilder.dom.domain.contract.ContractRepository;
import com.rotabuilder.dom.domain.contract.PatternContractLine;
import com.rotabuilder.dom.domain.employee.SkillProficiency;
import com.rotabuilder.dom.domain.pattern.Pattern;
import com.rotabuilder.dom.domain.pattern.PatternsRepository;
import com.rotabuilder.dom.domain.solver.EmployeeRoster;
import com.rotabuilder.dom.domain.solver.EmployeeRosterParametrization;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.message.MessageService;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

@DomainService(
        nature = NatureOfService.DOMAIN
)
public class ShiftService {

    public List<ShiftAssignment> plan() {
        SolverFactory<Solution> solverFactory = SolverFactory.createFromXmlResource("planner/EmployeeRosteringSolverConfig.xml");
        Solver solver = solverFactory.buildSolver();

        EmployeeRoster employeeRoster = new EmployeeRoster();
        employeeRoster.setCode("TEST");
        employeeRoster.setSkillList(employeeRepository.listSkills());
        employeeRoster.setShiftTypeList(shiftRepository.listShiftTypes());
        employeeRoster.setShiftTypeSkillRequirementList(generateShiftTypeSkillRequirements());
        employeeRoster.setPatternList(patternsRepository.listAll());
        employeeRoster.setContractList(contractRepository.listAll());
        employeeRoster.setContractLineList(generateContractLines());
        employeeRoster.setPatternContractLineList(generateContractPatternList());
        employeeRoster.setEmployeeList(employeeRepository.listAll());
        employeeRoster.setSkillProficiencyList(generateSkillProficiency());
        employeeRoster.setShiftDateList(shiftRepository.listShiftDates());
        employeeRoster.setShiftList(shiftRepository.listShifts());
        employeeRoster.setDayOffRequestList(employeeRepository.listDayOffRequests());
        employeeRoster.setDayOnRequestList(employeeRepository.listDayOnRequests());
        employeeRoster.setShiftOffRequestList(employeeRepository.listShiftOffRequests());
        employeeRoster.setShiftOnRequestList(employeeRepository.listShiftOnRequests());
        employeeRoster.setEmployeeRosterParametrization(generateEmployeeRosterInfo());
        employeeRoster.setShiftAssignmentList(generateAssigments());

        employeeRoster = (EmployeeRoster) solver.solve(employeeRoster);
        final HardSoftScore score = employeeRoster.getScore();
        for (ShiftAssignment shiftAssignment : employeeRoster.getShiftAssignmentList()) {
            shiftRepository.createShiftAssignment(shiftAssignment.getShift(), shiftAssignment.getEmployee());
        }
        messageService.informUser(String.format("'%s' score ", score.toString()));

        return shiftRepository.listShiftAssignments();
    }

    private List<ShiftAssignment> generateAssigments() {
        List<ShiftAssignment> shiftAssignments = new ArrayList<>();
        for (Shift shift : shiftRepository.listShifts()) {
            final ShiftAssignment shiftAssignment = new ShiftAssignment();
            shiftAssignment.setShift(shift);
            shiftAssignments.add(shiftAssignment);
        }
        return shiftAssignments;
    }

    private List<SkillProficiency> generateSkillProficiency() {
        List<SkillProficiency> lines = new ArrayList<>();
        for (Employee employee : employeeRepository.listAll()) {
            for (Skill skill : employee.getSkills()) {
                SkillProficiency skillProficiency = new SkillProficiency();
                skillProficiency.setEmployee(employee);
                skillProficiency.setSkill(skill);
                lines.add(skillProficiency);
            }
        }
        return lines;
    }

    private List<PatternContractLine> generateContractPatternList() {
        List<PatternContractLine> lines = new ArrayList<>();
        for (Contract contract : contractRepository.listAll()) {
            for (Pattern pattern : contract.getPatternList()) {
                final PatternContractLine patternContractLine = new PatternContractLine();
                patternContractLine.setContract(contract);
                patternContractLine.setPattern(pattern);
                lines.add(patternContractLine);
            }
        }
        return lines;
    }

    private List<ContractLine> generateContractLines() {
        List<ContractLine> contractLines = new ArrayList<>();
        final List<BooleanContractLine> booleanContractLines = contractRepository.listBooleanContractLineS();
        final List<MinMaxContractLine> minMaxContractLines = contractRepository.listMinMaxContractLines();
        contractLines.addAll(booleanContractLines);
        contractLines.addAll(minMaxContractLines);
        return contractLines;
    }

    private List<ShiftTypeSkillRequirement> generateShiftTypeSkillRequirements() {
        List<ShiftTypeSkillRequirement> shiftTypeSkillRequirements = new ArrayList<>();
        for (ShiftType shiftType : shiftRepository.listShiftTypes()) {
            for (Skill skill : shiftType.getSkills()) {
                final ShiftTypeSkillRequirement requirement = new ShiftTypeSkillRequirement();
                requirement.setShiftType(shiftType);
                requirement.setSkill(skill);
                shiftTypeSkillRequirements.add(requirement);
            }
        }
        return shiftTypeSkillRequirements;
    }

    private EmployeeRosterParametrization generateEmployeeRosterInfo() {
        List<ShiftDate> shiftDateList = shiftRepository.listShiftDates();
        EmployeeRosterParametrization employeeRosterParametrization = new EmployeeRosterParametrization();
        employeeRosterParametrization.setFirstShiftDate(shiftDateList.get(0));
        employeeRosterParametrization.setLastShiftDate(shiftDateList.get(shiftDateList.size() - 1));
        employeeRosterParametrization.setPlanningWindowStart(shiftDateList.get(0));
        return employeeRosterParametrization;
    }

    @javax.inject.Inject
    ShiftRepository shiftRepository;

    @javax.inject.Inject
    EmployeeRepository employeeRepository;

    @javax.inject.Inject
    PatternsRepository patternsRepository;

    @javax.inject.Inject
    ContractRepository contractRepository;

    @javax.inject.Inject
    MessageService messageService;
}
