package com.rotabuilder.fixture.dom;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class SkillTearDown extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
        isisJdoSupport.executeUpdate("delete from \"simple\".\"Skill\"");
    }


    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
