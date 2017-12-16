package com.rotabuilder.fixture.dom;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class ShiftTypeTearDown extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
        isisJdoSupport.executeUpdate("delete from \"simple\".\"ShiftTypeSkills\"");
        isisJdoSupport.executeUpdate("delete from \"simple\".\"ShiftType\"");
    }


    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
