package com.rotabuilder.dom.domain.employee;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.jdo.annotations.IdentityType;

import com.rotabuilder.dom.domain.contract.Contract;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.SemanticsOf;

@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM Employee "
                        + "WHERE name.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@XStreamAlias("Employee")
public class Employee extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String code;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String name;

    @javax.jdo.annotations.Column(allowsNull = "true")
    private Contract contract;

    @javax.jdo.annotations.Persistent(table = "EmployeeSkills")
    @javax.jdo.annotations.Join()
    private Set<Skill> skills = new TreeSet<>();

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Action
    public Employee addSkill(Skill skill) {
        getSkills().add(skill);
        return this;
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public Employee removeSkill(Skill skill) {
        getSkills().remove(skill);
        return this;
    }

    private List<DayOffRequest> dayOffRequests;

    private List<DayOnRequest> dayOnRequests;

    private List<ShiftOffRequest> shiftOffRequests;

    private List<ShiftOnRequest> shiftOnRequests;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getWeekendLength() {
        return getContract() != null ? getContract().getWeekendLength() : -1;
    }

    @javax.jdo.annotations.Persistent(mappedBy = "employee", defaultFetchGroup = "true")
    public List<DayOffRequest> getDayOffRequests() {
        return dayOffRequests;
    }

    public void setDayOffRequests(List<DayOffRequest> dayOffRequests) {
        this.dayOffRequests = dayOffRequests;
    }

    @javax.jdo.annotations.Persistent(mappedBy = "employee", defaultFetchGroup = "true")
    public List<DayOnRequest> getDayOnRequests() {
        return dayOnRequests;
    }

    public void setDayOnRequests(List<DayOnRequest> dayOnRequests) {
        this.dayOnRequests = dayOnRequests;
    }

    @javax.jdo.annotations.Persistent(mappedBy = "employee", defaultFetchGroup = "true")
    public List<ShiftOffRequest> getShiftOffRequests() {
        return shiftOffRequests;
    }

    public void setShiftOffRequests(List<ShiftOffRequest> shiftOffRequests) {
        this.shiftOffRequests = shiftOffRequests;
    }

    @javax.jdo.annotations.Persistent(mappedBy = "employee", defaultFetchGroup = "true")
    public List<ShiftOnRequest> getShiftOnRequests() {
        return shiftOnRequests;
    }

    public void setShiftOnRequests(List<ShiftOnRequest> shiftOnRequests) {
        this.shiftOnRequests = shiftOnRequests;
    }

    @Override
    public String toString() {
        if (name == null) {
            return super.toString();
        }
        return name;
    }

}
