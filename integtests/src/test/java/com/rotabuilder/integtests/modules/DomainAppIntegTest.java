package com.rotabuilder.integtests.modules;

import org.junit.BeforeClass;

import org.apache.isis.core.integtestsupport.IntegrationTestAbstract;
import org.apache.isis.core.integtestsupport.scenarios.ScenarioExecutionForIntegration;

public abstract class DomainAppIntegTest extends IntegrationTestAbstract {

    @BeforeClass
    public static void initClass() {
        org.apache.log4j.PropertyConfigurator.configure("logging.properties");
        DomainAppSystemInitializer.initIsft();

        // instantiating will install onto ThreadLocal
        new ScenarioExecutionForIntegration();
    }

}
