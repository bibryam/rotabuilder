package com.rotabuilder.dom.domain.contract;

import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.InheritanceStrategy;

import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamInclude;

@XStreamAlias("ContractLine")
@XStreamInclude({
        BooleanContractLine.class,
        MinMaxContractLine.class
})
@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Discriminator(
        strategy = DiscriminatorStrategy.CLASS_NAME,
        column = "discriminator")
public abstract class ContractLine extends AbstractPersistable {

    private Contract contract;

    @javax.jdo.annotations.Column(name = "leaseId", allowsNull = "false")
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @javax.jdo.annotations.Column(allowsNull = "false")
    private ContractLineType contractLineType;


    public ContractLineType getContractLineType() {
        return contractLineType;
    }

    public void setContractLineType(ContractLineType contractLineType) {
        this.contractLineType = contractLineType;
    }

    public abstract boolean isEnabled();

    @Override
    public String toString() {
        return contractLineType.toString();
    }

    @javax.inject.Inject
    ContractRepository contractRepository;

}
