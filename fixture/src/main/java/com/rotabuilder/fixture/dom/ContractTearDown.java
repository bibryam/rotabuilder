package com.rotabuilder.fixture.dom;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class ContractTearDown extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
        isisJdoSupport.executeUpdate("delete from \"simple\".\"ContractLine\"");
        isisJdoSupport.executeUpdate("delete from \"simple\".\"Contract\"");
    }

    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
