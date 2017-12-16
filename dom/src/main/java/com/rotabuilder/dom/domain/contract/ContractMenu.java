package com.rotabuilder.dom.domain.contract;

import java.util.List;

import com.rotabuilder.dom.domain.pattern.WeekendDefinition;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Contract.class
)
@DomainServiceLayout(
        named = "Contracts",
        menuOrder = "30"
)
public class ContractMenu {
    public static class CreateDomainEvent extends ActionDomainEvent<ContractMenu> {}


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<Contract> listContracts() {
        return contractRepository.listAll();
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "2")
    public Contract createContract(
            @ParameterLayout(named="Code")
            String code,
            @ParameterLayout(named="Description")
            String description,
            @ParameterLayout(named="WeekendDefinition")
            WeekendDefinition weekendDefinition) {
        return contractRepository.create(code, description, weekendDefinition);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "3")
    public List<BooleanContractLine> listBooleanContractLines() {
        return contractRepository.listBooleanContractLineS();
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "4")
    public BooleanContractLine createBooleanContractLine(
            @ParameterLayout(named = "Contract")
            Contract contract,

            @ParameterLayout(named = "ContractLineType")
            ContractLineType contractLineType,

            @ParameterLayout(named = "enabled")
            boolean enabled,

            @ParameterLayout(named = "weight")
            int weight
    ) {
       return contractRepository.createBooleanContractLine(contract, contractLineType, enabled, weight);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "5")
    public List<MinMaxContractLine> listMinMaxContractLines() {
        return contractRepository.listMinMaxContractLines();
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "6")
    public MinMaxContractLine createMinMaxContractLine(
            @ParameterLayout(named="Contract")
            Contract contract,

            @ParameterLayout(named="ContractLineType")
            ContractLineType contractLineType,

            @ParameterLayout(named="minimumEnabled")
            boolean minimumEnabled,

            @ParameterLayout(named="minimumValue")
            int minimumValue,

            @ParameterLayout(named="minimumWeight")
            int minimumWeight,

            @ParameterLayout(named="maximumEnabled")
            boolean maximumEnabled,

            @ParameterLayout(named="maximumValue")
            int maximumValue,

            @ParameterLayout(named="maximumWeight")
            int maximumWeight

    ) {
        return contractRepository.createMinMaxContractLine(contract, contractLineType, minimumEnabled, minimumValue,
                minimumWeight, maximumEnabled, maximumValue, maximumWeight);
    }

    @javax.inject.Inject
    ContractRepository contractRepository;

}
