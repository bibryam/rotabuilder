package com.rotabuilder.fixture.dom;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class EmployeeTearDown extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
        isisJdoSupport.executeUpdate("delete from \"simple\".\"EmployeeSkills\"");
        isisJdoSupport.executeUpdate("delete from \"simple\".\"Employee\"");
    }

    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
