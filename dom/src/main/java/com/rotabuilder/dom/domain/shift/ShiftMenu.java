package com.rotabuilder.dom.domain.shift;

import java.util.List;

import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.pattern.DayOfWeek;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Shift.class
)
@DomainServiceLayout(
        named = "Shifts",
        menuOrder = "50"
)
public class ShiftMenu {
    public static class CreateDomainEvent extends ActionDomainEvent<ShiftMenu> {}

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<ShiftType> listShiftTypes() {
        return shiftRepository.listShiftTypes();
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "2")
    public ShiftType createShiftType(
            @ParameterLayout(named = "Code") String code,
            @ParameterLayout(named = "Description") String description,
            @ParameterLayout(named = "StartTimeString") String startTimeString,
            @ParameterLayout(named = "EndTimeString") String endTimeString,
            @ParameterLayout(named = "Index") int index,
            @ParameterLayout(named = "IsNight") boolean isNight) {
        return shiftRepository.createShiftType(code, description, startTimeString, endTimeString, index, isNight);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "3")
    public List<ShiftDate> listShiftDates() {
        return shiftRepository.listShiftDates();
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "4")
    public ShiftDate createShiftDate(
            @ParameterLayout(named = "dateString") String dateString,
            @ParameterLayout(named = "dayIndex") int dayIndex,
            @ParameterLayout(named = "dayOfWeek") DayOfWeek dayOfWeek) {
        return shiftRepository.createShiftDate(dateString, dayIndex, dayOfWeek);
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "5")
    public List<Shift> listShifts() {
        return shiftRepository.listShifts();
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "6")
    public Shift createShift(
            @ParameterLayout(named = "shiftType") ShiftType shiftType,
            @ParameterLayout(named = "shiftDate") ShiftDate shiftDate,
            @ParameterLayout(named = "requiredEmployeeSize") int requiredEmployeeSize) {
        return shiftRepository.createShift(shiftType, shiftDate, requiredEmployeeSize);
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "7")
    public ShiftAssignment createShiftAssignment(
            @ParameterLayout(named = "shift") Shift shift,
            @ParameterLayout(named = "employee") Employee employee) {
        return shiftRepository.createShiftAssignment(shift, employee);
    }




    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "8")
    public List<ShiftAssignment> listShiftAssignments() {
        return shiftRepository.listShiftAssignments();
    }

    @javax.inject.Inject
    ShiftRepository shiftRepository;

}
