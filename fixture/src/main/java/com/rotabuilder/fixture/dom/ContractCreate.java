package com.rotabuilder.fixture.dom;

import java.util.ArrayList;
import java.util.List;

import com.rotabuilder.dom.domain.contract.BooleanContractLine;
import com.rotabuilder.dom.domain.contract.Contract;
import com.rotabuilder.dom.domain.contract.ContractLine;
import com.rotabuilder.dom.domain.contract.ContractRepository;
import com.rotabuilder.dom.domain.contract.MinMaxContractLine;
import com.rotabuilder.dom.domain.pattern.WeekendDefinition;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class ContractCreate extends FixtureScript {

    //region > name (input)
    private String code;
    private String description;
    private WeekendDefinition weekendDefinition;
    private List<BooleanContractLine> booleanContractLines = new ArrayList<>();
    private List<MinMaxContractLine> minMaxContractLines = new ArrayList<>();
    private List<ContractLine> contractLines = new ArrayList<>();

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

    public List<BooleanContractLine> getBooleanContractLines() {
        return booleanContractLines;
    }

    public void setBooleanContractLines(List<BooleanContractLine> booleanContractLines) {
        this.booleanContractLines = booleanContractLines;
    }

    public List<MinMaxContractLine> getMinMaxContractLines() {
        return minMaxContractLines;
    }

    public void setMinMaxContractLines(List<MinMaxContractLine> minMaxContractLines) {
        this.minMaxContractLines = minMaxContractLines;
    }

    public List<ContractLine> getContractLines() {
        return contractLines;
    }

    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    @Override
    protected void execute(final ExecutionContext ec) {

        String code = checkParam("code", ec, String.class);
        String description = checkParam("description", ec, String.class);
        WeekendDefinition weekendDefinition = checkParam("weekendDefinition", ec, WeekendDefinition.class);
        List<BooleanContractLine> booleanContractLines = checkParam("booleanContractLines", ec, List.class);
        List<MinMaxContractLine> minMaxContractLines = checkParam("minMaxContractLines", ec, List.class);

        this.contract = wrap(contractRepository).create(
                code,
                description,
                weekendDefinition);

        // also make available to UI
        ec.addResult(this, contract);

        for (BooleanContractLine line : booleanContractLines) {
            BooleanContractLine booleanContractLine = wrap(contractRepository).createBooleanContractLine(contract, line.getContractLineType(), line.isEnabled(), line.getWeight());
            ec.addResult(this, booleanContractLine);
            contractLines.add(booleanContractLine);
        }

        for (MinMaxContractLine line : minMaxContractLines) {
            MinMaxContractLine minMaxContractLine = wrap(contractRepository).createMinMaxContractLine(
                    contract, line.getContractLineType(),
                    line.isMinimumEnabled(), line.getMinimumValue(), line.getMinimumWeight(),
                    line.isMaximumEnabled(), line.getMaximumValue(), line.getMaximumWeight());
            ec.addResult(this, minMaxContractLine);
            contractLines.add(minMaxContractLine);
        }
    }

    @javax.inject.Inject
    private ContractRepository contractRepository;

}
