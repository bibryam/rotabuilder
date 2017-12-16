package com.rotabuilder.dom.domain.employee;

import java.util.List;

import com.rotabuilder.dom.domain.contract.Contract;
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
        repositoryFor = Employee.class
)
@DomainServiceLayout(
        named = "Employees",
        menuOrder = "20"
)
public class EmployeeMenu {
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<Employee> listAll() {
        return employeeRepository.listAll();
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Employee> findByName(
            @ParameterLayout(named="Name")
            final String name
    ) {
        return employeeRepository.findByName(name);
    }

    public static class CreateDomainEvent extends ActionDomainEvent<EmployeeMenu> {}
    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "3")
    public Employee create(
            @ParameterLayout(named="Name")
            final String name,
            @ParameterLayout(named="Code")
            final String code,
            @ParameterLayout(named="Contract")
            final Contract contract) {
        return employeeRepository.create(name, code, contract);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "4")
    public List<Skill> listSkills() {
        return employeeRepository.listSkills();
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "5")
    public Skill createSkill(
            @ParameterLayout(named="Code")
            final String code) {
        return employeeRepository.createSkill(code);
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "6")
    public List<DayOffRequest> listDayOffRequests() {
        return employeeRepository.listDayOffRequests();
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "7")
    public List<DayOnRequest> listDayOnRequests() {
        return employeeRepository.listDayOnRequests();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "8")
    public List<ShiftOnRequest> listShiftOnRequests() {
        return employeeRepository.listShiftOnRequests();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "9")
    public List<ShiftOffRequest> listShiftOffRequests() {
        return employeeRepository.listShiftOffRequests();
    }

    @javax.inject.Inject
    EmployeeRepository employeeRepository;

}
