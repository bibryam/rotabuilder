package com.rotabuilder.dom.domain.employee;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("SkillProficiency")
public class SkillProficiency {

    private Employee employee;

    private Skill skill;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return employee + "-" + skill;
    }

}
