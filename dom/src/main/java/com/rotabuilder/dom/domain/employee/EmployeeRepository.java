package com.rotabuilder.dom.domain.employee;

import java.util.List;

import com.rotabuilder.dom.domain.contract.Contract;
import com.rotabuilder.dom.domain.shift.Shift;
import com.rotabuilder.dom.domain.shift.ShiftDate;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Employee.class
)
public class EmployeeRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<Employee> listAll() {
        return repositoryService.allInstances(Employee.class);
    }

    public List<Employee> findByName(final String name) {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Employee.class,
                        "findByName",
                        "name", name));
    }

    public Employee create(String name, String code, Contract contract) {
        final Employee object = new Employee();
        object.setName(name);
        object.setCode(code);
        object.setContract(contract);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public DayOffRequest createDayOffRequest(Employee employee, ShiftDate shiftDate, int weight) {
        final DayOffRequest object = new DayOffRequest();
        object.setEmployee(employee);
        object.setShiftDate(shiftDate);
        object.setWeight(weight);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public DayOnRequest createDayOnRequest(Employee employee, ShiftDate shiftDate, int weight) {
        final DayOnRequest object = new DayOnRequest();
        object.setEmployee(employee);
        object.setShiftDate(shiftDate);
        object.setWeight(weight);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public ShiftOffRequest createShiftOffRequest(Employee employee, Shift shift, int weight) {
        final ShiftOffRequest object = new ShiftOffRequest();
        object.setEmployee(employee);
        object.setShift(shift);
        object.setWeight(weight);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public ShiftOnRequest createShiftOnRequest(Employee employee, Shift shift, int weight) {
        final ShiftOnRequest object = new ShiftOnRequest();
        object.setEmployee(employee);
        object.setShift(shift);
        object.setWeight(weight);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public List<Skill> listSkills() {
        return repositoryService.allInstances(Skill.class);
    }

    public List<Skill> findSkillsByName(final String code) {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Skill.class,
                        "findByName",
                        "code", code));
    }

    public Skill createSkill(final String code) {
        Skill object = new Skill();
        object.setCode(code);
        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public List<DayOffRequest> listDayOffRequests() {
        return repositoryService.allInstances(DayOffRequest.class);
    }

    public List<DayOnRequest> listDayOnRequests() {
        return repositoryService.allInstances(DayOnRequest.class);
    }

    public List<ShiftOnRequest> listShiftOnRequests() {
        return repositoryService.allInstances(ShiftOnRequest.class);
    }

    public List<ShiftOffRequest> listShiftOffRequests() {
        return repositoryService.allInstances(ShiftOffRequest.class);
    }
}
