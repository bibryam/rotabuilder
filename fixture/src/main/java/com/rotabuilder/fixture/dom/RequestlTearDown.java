package com.rotabuilder.fixture.dom;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class RequestlTearDown extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
        isisJdoSupport.executeUpdate("delete from \"simple\".\"DayOnRequest\"");
        isisJdoSupport.executeUpdate("delete from \"simple\".\"DayOffRequest\"");
        isisJdoSupport.executeUpdate("delete from \"simple\".\"ShiftOnRequest\"");
        isisJdoSupport.executeUpdate("delete from \"simple\".\"ShiftOffRequest\"");
    }

    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
