package com.rotabuilder.dom.domain.shift;

import com.rotabuilder.dom.domain.employee.Skill;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ShiftTypeSkillRequirement")
public class ShiftTypeSkillRequirement {

    private ShiftType shiftType;

    private Skill skill;

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return shiftType + "-" + skill;
    }

}
