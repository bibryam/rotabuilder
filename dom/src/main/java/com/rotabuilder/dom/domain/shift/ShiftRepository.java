package com.rotabuilder.dom.domain.shift;

import java.util.List;

import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.pattern.DayOfWeek;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Shift.class
)
public class ShiftRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<ShiftType> listShiftTypes() {
        return repositoryService.allInstances(ShiftType.class);
    }
    public List<ShiftDate> listShiftDates() {
        return repositoryService.allInstances(ShiftDate.class);
    }

    public List<Shift> listShifts() {
        return repositoryService.allInstances(Shift.class);
    }

    public ShiftType createShiftType(String code, String description, String startTimeString, String endTimeString, int index, boolean isNight) {
        final ShiftType object = new ShiftType();
        object.setCode(code);
        object.setDescription(description);
        object.setStartTimeString(startTimeString);
        object.setEndTimeString(endTimeString);
        object.setIndex(index);
        object.setNight(isNight);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public ShiftDate createShiftDate(String dateString, int dayIndex, DayOfWeek dayOfWeek) {
        final ShiftDate object = new ShiftDate();
        object.setDateString(dateString);
        object.setDayIndex(dayIndex);
        object.setDayOfWeek(dayOfWeek);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public Shift createShift(ShiftType shiftType, ShiftDate shiftDate, int requiredEmployeeSize) {
        final Shift object = new Shift();
        object.setShiftType(shiftType);
        object.setShiftDate(shiftDate);
        object.setRequiredEmployeeSize(requiredEmployeeSize);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public ShiftAssignment createShiftAssignment(Shift shift, Employee employee) {
        final ShiftAssignment object = new ShiftAssignment();
        object.setShift(shift);
        object.setEmployee(employee);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public List<ShiftAssignment> listShiftAssignments() {
        return repositoryService.allInstances(ShiftAssignment.class);
    }

    @Programmatic
    public List<ShiftType> autoComplete(final String description) {
        return repositoryService.allInstances(ShiftType.class);
    }

    @Programmatic
    public List<ShiftDate> autoCompleteShiftDate(final String description) {
        return repositoryService.allInstances(ShiftDate.class);
    }

}
