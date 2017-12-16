package com.rotabuilder.fixture.dom;

import com.rotabuilder.dom.domain.employee.EmployeeMenu;
import com.rotabuilder.dom.domain.employee.Skill;
import com.rotabuilder.fixture.scenarios.MyFixtureScript;

public class SkillCreate extends MyFixtureScript {

    //region > name (input)
    private String code;
    /**
     * Name of the object (required)
     */
    public String getCode() {
        return code;
    }

    public SkillCreate setName(final String code) {
        this.code = code;
        return this;
    }
    //endregion


    //region > nurse (output)
    private Skill skill;

    /**
     * The created simple object (output).
     * @return
     */
    public Skill getSkill() {
        return skill;
    }
    //endregion

    @Override
    protected void doExecute(final ExecutionContext ec) {

        String code = checkParam("code", ec, String.class);

        this.skill = wrap(employeeMenu).createSkill(code);

        // also make available to UI
        ec.addResult(this, skill);
    }

    @javax.inject.Inject
    private EmployeeMenu employeeMenu;

}
