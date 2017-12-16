package com.rotabuilder.dom.domain.contract;

import javax.jdo.annotations.InheritanceStrategy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@javax.jdo.annotations.PersistenceCapable
@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)

@XStreamAlias("MinMaxContractLine")
public class MinMaxContractLine extends ContractLine {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private boolean minimumEnabled;

    @javax.jdo.annotations.Column(allowsNull = "true")
    private int minimumValue;

    @javax.jdo.annotations.Column(allowsNull = "true")
    private int minimumWeight;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private boolean maximumEnabled;

    @javax.jdo.annotations.Column(allowsNull = "true")
    private int maximumValue;

    @javax.jdo.annotations.Column(allowsNull = "true")
    private int maximumWeight;

    public boolean isMinimumEnabled() {
        return minimumEnabled;
    }

    public void setMinimumEnabled(boolean minimumEnabled) {
        this.minimumEnabled = minimumEnabled;
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public int getMinimumWeight() {
        return minimumWeight;
    }

    public void setMinimumWeight(int minimumWeight) {
        this.minimumWeight = minimumWeight;
    }

    public boolean isMaximumEnabled() {
        return maximumEnabled;
    }

    public void setMaximumEnabled(boolean maximumEnabled) {
        this.maximumEnabled = maximumEnabled;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public int getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(int maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public boolean isEnabled() {
        return minimumEnabled || maximumEnabled;
    }

}
