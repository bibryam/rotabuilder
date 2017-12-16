package com.rotabuilder.dom.domain.contract;

import java.util.List;

import com.rotabuilder.dom.domain.pattern.WeekendDefinition;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Contract.class
)
public class ContractRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<Contract> listAll() {
        return repositoryService.allInstances(Contract.class);
    }

    public Contract create(String code, String description, WeekendDefinition weekendDefinition
                                      ) {
        final Contract object = new Contract();
        object.setCode(code);
        object.setDescription(description);
        object.setWeekendDefinition(weekendDefinition);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    @Programmatic
    public List<Contract> autoComplete(final String description) {
        return repositoryService.allInstances(Contract.class);
    }


    public List<BooleanContractLine> listBooleanContractLineS() {
        return repositoryService.allInstances(BooleanContractLine.class);
    }

    public BooleanContractLine createBooleanContractLine(Contract contract, ContractLineType contractLineType, boolean enabled, int weight) {
        final BooleanContractLine object = new BooleanContractLine();
        object.setContract(contract);
        object.setContractLineType(contractLineType);
        object.setEnabled(enabled);
        object.setWeight(weight);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }


    public List<MinMaxContractLine> listMinMaxContractLines() {
        return repositoryService.allInstances(MinMaxContractLine.class);
    }

    public MinMaxContractLine createMinMaxContractLine(Contract contract, ContractLineType contractLineType,
                                     boolean minimumEnabled,
                                     int minimumValue,
                                     int minimumWeight,
                                     boolean maximumEnabled,
                                     int maximumValue,
                                     int maximumWeight) {
        final MinMaxContractLine object = new MinMaxContractLine();
        object.setContract(contract);
        object.setContractLineType(contractLineType);
        object.setMinimumEnabled(minimumEnabled);
        object.setMinimumValue(minimumValue);
        object.setMinimumWeight(minimumWeight);
        object.setMaximumEnabled(maximumEnabled);
        object.setMaximumValue(maximumValue);
        object.setMaximumWeight(maximumWeight);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }



}
