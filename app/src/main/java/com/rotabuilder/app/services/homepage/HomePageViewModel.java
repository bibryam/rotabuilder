package com.rotabuilder.app.services.homepage;

import java.util.List;

import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.employee.EmployeeRepository;
import com.rotabuilder.dom.domain.shift.ShiftAssignment;
import com.rotabuilder.dom.domain.shift.ShiftService;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.ViewModel;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.wrapper.WrapperFactory;

@ViewModel
public class HomePageViewModel {

    //region > title
    public TranslatableString title() {
        return TranslatableString.tr("{num} employees", "num", getEmployees().size());
    }
    //endregion

    //region > object (collection)
    @org.apache.isis.applib.annotation.HomePage
    public List<Employee> getEmployees() {
        return wrapperFactory.wrap(employeeRepository).listAll();
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public List<ShiftAssignment> startPlanning() {
        return shiftService.plan();
    }

    @javax.inject.Inject
    EmployeeRepository employeeRepository;


    @javax.inject.Inject
    ShiftService shiftService;


    @javax.inject.Inject
    WrapperFactory wrapperFactory;

    //endregion
}
