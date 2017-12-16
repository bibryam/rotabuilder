package com.rotabuilder.fixture.scenarios;

import java.util.HashSet;
import java.util.List;

import com.google.common.collect.Lists;

import com.rotabuilder.fixture.dom.ShiftTypeTearDown;
import org.apache.isis.applib.annotation.Programmatic;

import com.rotabuilder.dom.domain.employee.EmployeeRepository;
import com.rotabuilder.dom.domain.employee.Skill;
import com.rotabuilder.dom.domain.shift.ShiftType;
import com.rotabuilder.fixture.dom.ShiftTypeCreate;

public class RecreateShiftType extends MyFixtureScript {

    public RecreateShiftType() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    //region > number (optional input)
    private Integer number;

    /**
     * The number of objects to create, up to 10; optional, defaults to 3.
     */
    public Integer getNumber() {
        return number;
    }

    public RecreateShiftType setNumber(final Integer number) {
        this.number = number;
        return this;
    }
    //endregion

    //region > skills (output)
    private final List<ShiftType> shiftTypes = Lists.newArrayList();

    /**
     * The skills created by this fixture (output).
     */
    @Programmatic
    public List<ShiftType> getShiftTypes() {
        return shiftTypes;
    }
    //endregion

    @Override
    protected void doExecute(final ExecutionContext ec) {

        ec.executeChild(this, new ShiftTypeTearDown());

        final List<Skill> skills = employeeRepository.listSkills();

        ShiftTypeCreate shiftTypeCreateEarly = new ShiftTypeCreate();
        shiftTypeCreateEarly.setCode("E");
        shiftTypeCreateEarly.setDescription("Early");
        shiftTypeCreateEarly.setStartTimeString("06:30:00");
        shiftTypeCreateEarly.setEndTimeString("14:30:00");
        shiftTypeCreateEarly.setIndex(0);
        shiftTypeCreateEarly.setIsNight("06:30:00".compareTo("14:30:00") > 0);
        shiftTypeCreateEarly.setSkills(new HashSet<>(skills));

        ec.executeChild(this, shiftTypeCreateEarly.getCode(), shiftTypeCreateEarly);
        shiftTypes.add(shiftTypeCreateEarly.getShiftType());

        ShiftTypeCreate shiftTypeCreateLate = new ShiftTypeCreate();
        shiftTypeCreateLate.setCode("L");
        shiftTypeCreateLate.setDescription("Late");
        shiftTypeCreateLate.setStartTimeString("14:30:00");
        shiftTypeCreateLate.setEndTimeString("22:30:00");
        shiftTypeCreateLate.setIndex(1);
        shiftTypeCreateLate.setIsNight("14:30:00".compareTo("22:30:00") > 0);
        shiftTypeCreateLate.setSkills(new HashSet<>(skills));

        ec.executeChild(this, shiftTypeCreateLate.getCode(), shiftTypeCreateLate);
        shiftTypes.add(shiftTypeCreateLate.getShiftType());

        ShiftTypeCreate shiftTypeCreateNight = new ShiftTypeCreate();
        shiftTypeCreateNight.setCode("N");
        shiftTypeCreateNight.setDescription("Night");
        shiftTypeCreateNight.setStartTimeString("22:30:00");
        shiftTypeCreateNight.setEndTimeString("06:30:00");
        shiftTypeCreateNight.setIndex(2);
        shiftTypeCreateNight.setIsNight("22:30:00".compareTo("06:30:00") > 0);
        shiftTypeCreateNight.setSkills(new HashSet<>(skills));

        ec.executeChild(this, shiftTypeCreateNight.getCode(), shiftTypeCreateNight);
        shiftTypes.add(shiftTypeCreateNight.getShiftType());
    }


    @javax.inject.Inject
    private EmployeeRepository employeeRepository;
}
