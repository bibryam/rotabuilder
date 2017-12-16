package com.rotabuilder.fixture.dom;

import java.util.Set;

import com.rotabuilder.dom.domain.contract.Contract;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.employee.EmployeeMenu;
import com.rotabuilder.dom.domain.employee.Skill;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class EmployeeCreate extends FixtureScript {

    //region > name (input)
    private String name;
    private String code;
    private Contract contract;
    private Set<Skill> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    //region > nurse (output)
    private Employee employee;

    /**
     * The created simple object (output).
     * @return
     */
    public Employee getEmployee() {
        return employee;
    }
    //endregion

    @Override
    protected void execute(final ExecutionContext ec) {

        String name = checkParam("name", ec, String.class);
        String code = checkParam("code", ec, String.class);
        Contract contract = checkParam("contract", ec, Contract.class);
        Set<Skill> skills = checkParam("skills", ec, Set.class);

        this.employee = wrap(employeeMenu).create(name, code, contract);
        this.employee.setSkills(skills);

        // also make available to UI
        ec.addResult(this, employee);
    }

    @javax.inject.Inject
    private EmployeeMenu employeeMenu;

}
