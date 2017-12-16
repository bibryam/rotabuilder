package com.rotabuilder.app.services.homepage;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.registry.ServiceRegistry;

// trick to suppress the actions from the top-level menu
@DomainService(nature = NatureOfService.VIEW_CONTRIBUTIONS_ONLY)
public class HomePageService {

    //region > homePage (action)

    @Action(semantics = SemanticsOf.SAFE)
    @HomePage
    public HomePageViewModel homePage() {
        return serviceRegistry.injectServicesInto(new HomePageViewModel());
    }

    //endregion

    //region > injected services

    @javax.inject.Inject
    ServiceRegistry serviceRegistry;

    //endregion
}
