package com.rotabuilder.dom.domain.contract;

import javax.jdo.annotations.InheritanceStrategy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@javax.jdo.annotations.PersistenceCapable
@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)

@XStreamAlias("BooleanContractLine")
public class BooleanContractLine extends ContractLine {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private boolean enabled;

    @javax.jdo.annotations.Column(allowsNull = "true")
    private int weight;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
