package com.rotabuilder.fixture.scenarios;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import com.rotabuilder.fixture.dom.SkillCreate;
import org.apache.isis.applib.annotation.Programmatic;

import com.rotabuilder.dom.domain.employee.Skill;
import com.rotabuilder.fixture.dom.SkillTearDown;

public class RecreateSkills extends MyFixtureScript {

    public final List<String> NAMES = Collections.unmodifiableList(Arrays.asList(
            "Nurse", "HeadNurse"));

    public RecreateSkills() {
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

    public RecreateSkills setNumber(final Integer number) {
        this.number = number;
        return this;
    }
    //endregion

    //region > skills (output)
    private final List<Skill> skills = Lists.newArrayList();

    /**
     * The skills created by this fixture (output).
     */
    @Programmatic
    public List<Skill> getSkills() {
        return skills;
    }
    //endregion

    @Override
    protected void doExecute(final ExecutionContext ec) {

        // defaults
        final int number = defaultParam("number", ec, 2);

        // validate
        if(number < 0 || number > NAMES.size()) {
            throw new IllegalArgumentException(String.format("number must be in range [0,%d)", NAMES.size()));
        }

        //
        // execute
        //
        ec.executeChild(RecreateSkills.this, new SkillTearDown());

        for (int i = 0; i < number; i++) {
            final SkillCreate fs = new SkillCreate().setName(NAMES.get(i));
            ec.executeChild(RecreateSkills.this, fs.getCode(), fs);
            skills.add(fs.getSkill());
        }

    }

}
