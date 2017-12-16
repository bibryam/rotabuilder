package com.rotabuilder.dom.domain.solver;

import java.io.Serializable;
import javax.inject.Inject;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.isis.applib.FatalException;
import org.apache.isis.applib.annotation.Auditing;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.user.UserService;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancyRepository;
import org.isisaddons.module.security.dom.tenancy.WithApplicationTenancy;
import org.isisaddons.module.security.dom.user.ApplicationUser;
import org.isisaddons.module.security.dom.user.ApplicationUserRepository;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@javax.jdo.annotations.Inheritance(strategy=javax.jdo.annotations.InheritanceStrategy.SUBCLASS_TABLE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column="id")

@javax.jdo.annotations.Version(
        strategy= VersionStrategy.DATE_TIME,
        column="version")

@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED
)
public abstract class AbstractPersistable implements Serializable, Comparable<AbstractPersistable>, WithApplicationTenancy {

     private String applicationTenancyPath;

    @javax.jdo.annotations.Column(
            length = ApplicationTenancy.MAX_LENGTH_PATH,
            allowsNull = "false",
            name = "atPath"
    )
    @org.apache.isis.applib.annotation.Property(hidden = Where.EVERYWHERE)
    public String getApplicationTenancyPath() {
        return applicationTenancyPath;
    }

    public void setApplicationTenancyPath(final String applicationTenancyPath) {
        this.applicationTenancyPath = applicationTenancyPath;
    }

    @PropertyLayout(
            hidden = Where.ALL_TABLES,
            describedAs = "Determines those users for whom this object is available to view and/or modify."
    )

    @Programmatic
    public ApplicationTenancy getApplicationTenancy() {
        final ApplicationTenancy applicationTenancy = applicationTenancyRepository.findByPathCached(applicationTenancyPath);
        if (applicationTenancy == null) {
            throw new FatalException("Domain Object without Application Tenancy.");
        }
        return applicationTenancy;
    }

    public int compareTo(AbstractPersistable other) {
        return new CompareToBuilder()
                .append(getClass().getName(), other.getClass().getName())
                .toComparison();
    }

    public void init() {
        if (applicationTenancyPath == null) {
            final String username = userService.getUser().getName();
            final ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
            final ApplicationTenancy applicationTenancy = applicationUser.getTenancy();
            if(applicationTenancy == null) {
                throw new IllegalStateException(String.format("No application tenancy defined for user '%s'", username));
            }
            applicationTenancyPath = applicationTenancy.getPath();
        } else {
            throw new IllegalStateException(String.format("Application tenancy defined for object '%s'", this));
        }

    }

    public String toString() {
        return getClass().getName().replaceAll(".*\\.", "");
    }

    @Inject
    private ApplicationTenancyRepository applicationTenancyRepository;

    @javax.inject.Inject
    private ApplicationUserRepository applicationUserRepository;

    @javax.inject.Inject
    private UserService userService;

}
