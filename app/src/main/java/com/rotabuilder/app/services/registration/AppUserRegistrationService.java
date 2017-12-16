package com.rotabuilder.app.services.registration;

import java.util.Collections;
import java.util.Set;
import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.role.ApplicationRoleRepository;
import org.isisaddons.module.security.userreg.SecurityModuleAppUserRegistrationServiceAbstract;

@DomainService
public class AppUserRegistrationService extends SecurityModuleAppUserRegistrationServiceAbstract {
    protected ApplicationRole getInitialRole() {
        return findRole("isis-module-security-regular-user");
    }
    protected Set<ApplicationRole> getAdditionalInitialRoles() {
        return Collections.singleton(findRole("easter2017"));
    }
    private ApplicationRole findRole(final String roleName) {
        return applicationRoleRepository.findByNameCached(roleName);
    }
    @Inject
    private ApplicationRoleRepository applicationRoleRepository;
}