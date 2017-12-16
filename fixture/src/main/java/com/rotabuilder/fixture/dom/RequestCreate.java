package com.rotabuilder.fixture.dom;

import java.util.Set;

import com.rotabuilder.dom.domain.employee.DayOffRequest;
import com.rotabuilder.dom.domain.employee.DayOnRequest;
import com.rotabuilder.dom.domain.employee.EmployeeRepository;
import com.rotabuilder.dom.domain.employee.ShiftOffRequest;
import com.rotabuilder.dom.domain.employee.ShiftOnRequest;
import com.rotabuilder.fixture.scenarios.MyFixtureScript;

public class RequestCreate extends MyFixtureScript {
    private Set<DayOnRequest> dayOnRequests;
    private Set<DayOffRequest> dayOffRequests;
    private Set<ShiftOnRequest> shiftOnRequests;
    private Set<ShiftOffRequest> shiftOffRequests;

    public Set<DayOnRequest> getDayOnRequests() {
        return dayOnRequests;
    }

    public void setDayOnRequests(Set<DayOnRequest> dayOnRequests) {
        this.dayOnRequests = dayOnRequests;
    }

    public Set<DayOffRequest> getDayOffRequests() {
        return dayOffRequests;
    }

    public void setDayOffRequests(Set<DayOffRequest> dayOffRequests) {
        this.dayOffRequests = dayOffRequests;
    }

    public Set<ShiftOnRequest> getShiftOnRequests() {
        return shiftOnRequests;
    }

    public void setShiftOnRequests(Set<ShiftOnRequest> shiftOnRequests) {
        this.shiftOnRequests = shiftOnRequests;
    }

    public Set<ShiftOffRequest> getShiftOffRequests() {
        return shiftOffRequests;
    }

    public void setShiftOffRequests(Set<ShiftOffRequest> shiftOffRequests) {
        this.shiftOffRequests = shiftOffRequests;
    }

    @Override
    protected void doExecute(final ExecutionContext ec) {
        Set<DayOnRequest> dayOnRequests = checkParam("dayOnRequests", ec, Set.class);
        Set<DayOffRequest> dayOffRequests = checkParam("dayOffRequests", ec, Set.class);
        Set<ShiftOnRequest> shiftOnRequests = checkParam("shiftOnRequests", ec, Set.class);
        Set<ShiftOffRequest> shiftOffRequests = checkParam("shiftOffRequests", ec, Set.class);

        for (DayOnRequest dayOnRequest : dayOnRequests) {
            final DayOnRequest persisted = wrap(employeeRepository).createDayOnRequest(dayOnRequest.getEmployee(), dayOnRequest.getShiftDate(), dayOnRequest.getWeight());
            ec.addResult(this, persisted);
        }

        for (DayOffRequest dayOffRequest : dayOffRequests) {
            final DayOffRequest persisted = wrap(employeeRepository).createDayOffRequest(dayOffRequest.getEmployee(), dayOffRequest.getShiftDate(), dayOffRequest.getWeight());
            ec.addResult(this, persisted);
        }

        for (ShiftOnRequest shiftOnRequest : shiftOnRequests) {
            final ShiftOnRequest persisted = wrap(employeeRepository).createShiftOnRequest(shiftOnRequest.getEmployee(), shiftOnRequest.getShift(), shiftOnRequest.getWeight());
            ec.addResult(this, persisted);
        }

        for (ShiftOffRequest shiftOffRequest : shiftOffRequests) {
            final ShiftOffRequest persisted = wrap(employeeRepository).createShiftOffRequest(shiftOffRequest.getEmployee(), shiftOffRequest.getShift(), shiftOffRequest.getWeight());
            ec.addResult(this, persisted);
        }
    }

    @javax.inject.Inject
    private EmployeeRepository employeeRepository;
}
