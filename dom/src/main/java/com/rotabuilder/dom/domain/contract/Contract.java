package com.rotabuilder.dom.domain.contract;

import java.util.List;
import javax.jdo.annotations.IdentityType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.rotabuilder.dom.domain.pattern.Pattern;
import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import com.rotabuilder.dom.domain.pattern.WeekendDefinition;
import org.apache.isis.applib.annotation.DomainObject;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@DomainObject(
        autoCompleteRepository = ContractRepository.class,
        autoCompleteAction = "autoComplete")
@XStreamAlias("Contract")
public class Contract extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String code;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String description;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private WeekendDefinition weekendDefinition;

    private List<ContractLine> contractLineList;

    private List<Pattern> patternList;

    @javax.jdo.annotations.Persistent()
    public List<Pattern> getPatternList() {
        return patternList;
    }

    public void setPatternList(List<Pattern> patternList) {
        this.patternList = patternList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WeekendDefinition getWeekendDefinition() {
        return weekendDefinition;
    }

    public void setWeekendDefinition(WeekendDefinition weekendDefinition) {
        this.weekendDefinition = weekendDefinition;
    }

    @javax.jdo.annotations.Persistent(mappedBy = "contract", defaultFetchGroup = "true")
    public List<ContractLine> getContractLineList() {
        return contractLineList;
    }

    public void setContractLineList(List<ContractLine> contractLineList) {
        this.contractLineList = contractLineList;
    }

    @Override
    public String toString() {
        return code;
    }

    public int getWeekendLength() {
        return weekendDefinition.getWeekendLength();
    }

}
