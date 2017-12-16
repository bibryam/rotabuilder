package com.rotabuilder.app.services.tenancyevaluator;

import java.util.List;

import com.rotabuilder.app.services.homepage.HomePageService;
import com.rotabuilder.app.services.homepage.HomePageViewModel;
import org.apache.isis.applib.AbstractService;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.isisaddons.module.security.app.user.MeService;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancyRepository;
import org.isisaddons.module.security.dom.user.ApplicationUser;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY
)
@DomainServiceLayout(
        menuBar = DomainServiceLayout.MenuBar.TERTIARY
)
public class TenancySwitcher extends AbstractService {

    //region > switchTenancy (action)
    @Action(
            semantics = SemanticsOf.IDEMPOTENT
    )
    @ActionLayout(
            cssClassFa = "fa-exchange"
    )
    public HomePageViewModel switchTenancy(final ApplicationTenancy applicationTenancy) {
        final ApplicationUser applicationUser = meService.me();
        applicationUser.updateTenancy(applicationTenancy);
        return homePageService.homePage();
    }

    public boolean hideSwitchTenancy() {
        final ApplicationUser applicationUser = meService.me();
        return !"admin".equals(applicationUser.getName());
    }

    public List<ApplicationTenancy> choices0SwitchTenancy() {
        return applicationTenancyRepository.allTenancies();
    }

    public ApplicationTenancy default0SwitchTenancy() {
        final ApplicationUser applicationUser = meService.me();
        return applicationUser.getTenancy();
    }
    //endregion

    //region > injected services
    @javax.inject.Inject
    private MeService meService;

    @javax.inject.Inject
    private ApplicationTenancyRepository applicationTenancyRepository;

    @javax.inject.Inject
    private HomePageService homePageService;
    //endregion

}

