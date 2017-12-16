package com.rotabuilder.app.services.tenancyevaluator;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.userprof.UserProfileService;
import org.isisaddons.module.security.app.user.MeService;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;
import org.isisaddons.module.security.dom.user.ApplicationUser;

/**
 * Demonstrates how to provide a custom implementation of the {@link UserProfileService}.
 */
@DomainService(
        nature = NatureOfService.DOMAIN
)
public class UserProfileServiceShowingTenancy implements UserProfileService {

    @Override
    @Programmatic
    public String userProfileName() {
        final ApplicationUser applicationUser = meService.me();

        final StringBuilder buf = new StringBuilder();
        final String username = applicationUser.getName();
        final ApplicationTenancy tenancy = applicationUser.getTenancy();

        buf.append("Hi ");
        buf.append(username);
        if (tenancy.getName() != null) {
            buf.append(" @").append(tenancy.getName());
        }

        return buf.toString();
    }


    @Inject
    private MeService meService;
}
