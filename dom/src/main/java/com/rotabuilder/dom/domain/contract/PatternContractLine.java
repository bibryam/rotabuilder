package com.rotabuilder.dom.domain.contract;

import com.rotabuilder.dom.domain.pattern.Pattern;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PatternContractLine")
public class PatternContractLine {

    private Contract contract;

    private Pattern pattern;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return contract + "-" + pattern;
    }

}
