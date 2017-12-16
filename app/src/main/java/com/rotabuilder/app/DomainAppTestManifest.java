package com.rotabuilder.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rotabuilder.dom.DomainAppDomainModule;
import com.rotabuilder.fixture.DomainAppFixtureModule;
import org.apache.isis.applib.AppManifest;
import org.apache.isis.applib.fixturescripts.FixtureScript;

/**
 * Bootstrap the application.
 */
public class DomainAppTestManifest implements AppManifest {

    /**
     * Load all services and entities found in (the packages and subpackages within) these modules
     */
    @Override
    public List<Class<?>> getModules() {
        return Arrays.asList(
                DomainAppDomainModule.class,  // domain (entities and repositories)
                DomainAppFixtureModule.class, // fixtures
                DomainAppAppModule.class      // home page service etc
        );
    }

    /**
     * No additional services.
     */
    @Override
    public List<Class<?>> getAdditionalServices() {
        return Collections.emptyList();
    }

    /**
     * Use shiro for authentication.
     *
     * <p>
     *     NB: this is ignored for integration tests, which always use "bypass".
     * </p>
     */
    @Override
    public String getAuthenticationMechanism() {
        return "bypass";
    }

    /**
     * Use shiro for authorization.
     *
     * <p>
     *     NB: this is ignored for integration tests, which always use "bypass".
     * </p>
     */
    @Override
    public String getAuthorizationMechanism() {
        return "bypass";
    }

    /**
     * Fixtures to be installed.
     */
    @Override
    public List<Class<? extends FixtureScript>> getFixtures() {
        return Lists.newArrayList();
    }

    /**
     * Force fixtures to be loaded.
     */
    @Override
    public Map<String, String> getConfigurationProperties() {
        HashMap<String,String> props = Maps.newHashMap();
        props.put("isis.persistor.datanucleus.install-fixtures","true");
        return props;
    }

}
