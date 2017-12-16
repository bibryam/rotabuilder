package com.rotabuilder.fixture.dom;

import java.util.Set;

import com.rotabuilder.dom.domain.employee.Skill;
import com.rotabuilder.dom.domain.shift.ShiftMenu;
import com.rotabuilder.dom.domain.shift.ShiftType;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class ShiftTypeCreate extends FixtureScript {

    private String code;
    private String description;
    private String startTimeString;
    private String endTimeString;
    private int index;
    private boolean isNight;
    private Set<Skill> skills;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public boolean getIsNight() {
        return isNight;
    }

    public void setIsNight(boolean isNight) {
        this.isNight = isNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }
    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    //region > nurse (output)
    private ShiftType shiftType;

    public ShiftType getShiftType() {
        return shiftType;
    }
    //endregion

    @Override
    protected void execute(final ExecutionContext ec) {
        String code = checkParam("code", ec, String.class);
        String description = checkParam("description", ec, String.class);
        String startTimeString = checkParam("startTimeString", ec, String.class);
        String endTimeString = checkParam("endTimeString", ec, String.class);
        int index = checkParam("index", ec, Integer.class);
        boolean isNight = checkParam("isNight", ec, Boolean.class);
        Set<Skill> skills = checkParam("skills", ec, Set.class);

        this.shiftType = wrap(shiftMenu).createShiftType(code, description, startTimeString, endTimeString, index, isNight);
        this.shiftType.setSkills(skills);

        // also make available to UI
        ec.addResult(this, shiftType);
    }

    @javax.inject.Inject
    private ShiftMenu shiftMenu;

}
