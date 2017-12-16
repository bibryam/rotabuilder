package com.rotabuilder.fixture.scenarios;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import com.rotabuilder.dom.domain.employee.DayOffRequest;
import com.rotabuilder.dom.domain.employee.DayOnRequest;
import com.rotabuilder.dom.domain.employee.ShiftOffRequest;
import com.rotabuilder.dom.domain.employee.ShiftOnRequest;
import com.rotabuilder.dom.domain.shift.Shift;
import com.rotabuilder.dom.domain.shift.ShiftDate;
import com.rotabuilder.dom.domain.shift.ShiftRepository;
import com.rotabuilder.fixture.dom.EmployeeCreate;
import com.rotabuilder.fixture.dom.RequestCreate;
import com.rotabuilder.fixture.dom.RequestlTearDown;
import org.apache.isis.applib.annotation.Programmatic;

import com.rotabuilder.dom.domain.contract.Contract;
import com.rotabuilder.dom.domain.contract.ContractRepository;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.employee.EmployeeRepository;
import com.rotabuilder.dom.domain.employee.Skill;
import com.rotabuilder.fixture.dom.EmployeeTearDown;

public class RecreateEmployees extends MyFixtureScript {

    public final List<String> NAMES = Collections.unmodifiableList(Arrays.asList(
            "Amelia", "Emily", "Olivia", "Lily", "Sophia"));

    public RecreateEmployees() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    //region > number (optional input)
    private Integer number;

    /**
     * The number of objects to create, up to 10; optional, defaults to 3.
     */
    public Integer getNumber() {
        return number;
    }

    public RecreateEmployees setNumber(final Integer number) {
        this.number = number;
        return this;
    }
    //endregion

    //region > skills (output)
    private final List<Employee> employees = Lists.newArrayList();

    /**
     * The skills created by this fixture (output).
     */
    @Programmatic
    public List<Employee> getEmployees() {
        return employees;
    }
    //endregion

    @Override
    protected void doExecute(final ExecutionContext ec) {

        final List<Contract> contracts = contractRepository.listAll();
        final List<Skill> skills = employeeRepository.listSkills();
        final List<ShiftDate> shiftDates = shiftRepository.listShiftDates();
        final List<Shift> shifts = shiftRepository.listShifts();

        // defaults
        final int number = defaultParam("number", ec, 5);

        // validate
        if (number < 0 || number > NAMES.size()) {
            throw new IllegalArgumentException(String.format("number must be in range [0,%d)", NAMES.size()));
        }

        //
        // execute
        //
        ec.executeChild(this, new RequestlTearDown());
        ec.executeChild(this, new EmployeeTearDown());

        for (int i = 0; i < number; i++) {
            EmployeeCreate employeeCreate = new EmployeeCreate();
            employeeCreate.setName(NAMES.get(i));
            employeeCreate.setCode("" + i);
            employeeCreate.setContract(contracts.get(0));
            employeeCreate.setSkills(new HashSet<>(skills));

            ec.executeChild(this, employeeCreate.getName(), employeeCreate);
            final Employee employee = employeeCreate.getEmployee();
            employees.add(employee);

            RequestCreate requestCreate = new RequestCreate();
            requestCreate.setDayOnRequests(getDaysOn(shiftDates, employee));
            requestCreate.setDayOffRequests(getDaysOff(shiftDates, employee));
            requestCreate.setShiftOnRequests(getShiftOnRequests(shifts, employee));
            requestCreate.setShiftOffRequests(getShiftOffRequests(shifts, employee));

            ec.executeChild(this, requestCreate.getLocalName(), requestCreate);
        }
    }

    @javax.inject.Inject
    private ContractRepository contractRepository;

    @javax.inject.Inject
    private EmployeeRepository employeeRepository;

    @javax.inject.Inject
    private ShiftRepository shiftRepository;

    public Set<DayOnRequest> getDaysOn(List<ShiftDate> shiftDates, Employee employee) {
        Set daysOn = new HashSet<>();
        for (ShiftDate shiftDate : shiftDates) {
            DayOnRequest dayOnRequest = new DayOnRequest();
            dayOnRequest.setWeight(1);
            dayOnRequest.setShiftDate(shiftDate);
            dayOnRequest.setEmployee(employee);
            daysOn.add(dayOnRequest);
        }
        return daysOn;
    }

    private Set<DayOffRequest> getDaysOff(List<ShiftDate> shiftDates, Employee employee) {
        Set daysOff = new HashSet<>();
        for (ShiftDate shiftDate : shiftDates) {
            DayOffRequest dayOffRequest = new DayOffRequest();
            dayOffRequest.setWeight(1);
            dayOffRequest.setShiftDate(shiftDate);
            dayOffRequest.setEmployee(employee);
            daysOff.add(dayOffRequest);
        }
        return daysOff;
    }

    private Set<ShiftOnRequest> getShiftOnRequests(List<Shift> shifts, Employee employee) {
        Set shiftOns = new HashSet<>();
        for (Shift shift : shifts) {
            ShiftOnRequest shiftOnRequest = new ShiftOnRequest();
            shiftOnRequest.setWeight(1);
            shiftOnRequest.setShift(shift);
            shiftOnRequest.setEmployee(employee);
            shiftOns.add(shiftOnRequest);
        }
        return shiftOns;
    }

    private Set<ShiftOffRequest> getShiftOffRequests(List<Shift> shifts, Employee employee) {
        Set shiftOns = new HashSet<>();
        for (Shift shift : shifts) {
            ShiftOffRequest shiftOnRequest = new ShiftOffRequest();
            shiftOnRequest.setWeight(1);
            shiftOnRequest.setShift(shift);
            shiftOnRequest.setEmployee(employee);
            shiftOns.add(shiftOnRequest);
        }
        return shiftOns;
    }
}
