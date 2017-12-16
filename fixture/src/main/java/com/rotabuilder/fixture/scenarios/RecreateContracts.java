package com.rotabuilder.fixture.scenarios;

import java.util.List;

import com.google.common.collect.Lists;

import org.apache.isis.applib.annotation.Programmatic;

import com.rotabuilder.dom.domain.contract.BooleanContractLine;
import com.rotabuilder.dom.domain.contract.Contract;
import com.rotabuilder.dom.domain.contract.ContractLine;
import com.rotabuilder.dom.domain.contract.ContractLineType;
import com.rotabuilder.dom.domain.contract.MinMaxContractLine;
import com.rotabuilder.dom.domain.pattern.WeekendDefinition;
import com.rotabuilder.fixture.dom.ContractCreate;
import com.rotabuilder.fixture.dom.ContractTearDown;

public class RecreateContracts extends MyFixtureScript {

    public RecreateContracts() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    //region > skills (output)
    private List<Contract> contracts = Lists.newArrayList();
    private List<ContractLine> contractLines = Lists.newArrayList();
    @Programmatic
    public List<Contract> getContracts() {
        return contracts;
    }

    @Programmatic
    public List<ContractLine> getContractLines() {
        return contractLines;
    }

    @Override
    protected void doExecute(final ExecutionContext ec) {

        ec.executeChild(this, new ContractTearDown());

        ContractCreate contractCreate = new ContractCreate();
        contractCreate.setCode("0");
        contractCreate.setDescription("uniform");
        contractCreate.setWeekendDefinition(WeekendDefinition.SATURDAY_SUNDAY);

        List<BooleanContractLine> booleanContractLines = Lists.newArrayList();
        contractCreate.setBooleanContractLines(booleanContractLines);
        booleanContractLines.add(createBooleanContractLine(ContractLineType.SINGLE_ASSIGNMENT_PER_DAY, true, 1));
        booleanContractLines.add(createBooleanContractLine(ContractLineType.COMPLETE_WEEKENDS, true, 1));
        booleanContractLines.add(createBooleanContractLine(ContractLineType.IDENTICAL_SHIFT_TYPES_DURING_WEEKEND, true, 1));
        booleanContractLines.add(createBooleanContractLine(ContractLineType.NO_NIGHT_SHIFT_BEFORE_FREE_WEEKEND, true, 0));

        List<MinMaxContractLine> minMaxContractLines = Lists.newArrayList();
        contractCreate.setMinMaxContractLines(minMaxContractLines);
        minMaxContractLines.add(createMinMaxContractLine(ContractLineType.TOTAL_ASSIGNMENTS, true, 3, 1, true, 5, 1));
        minMaxContractLines.add(createMinMaxContractLine(ContractLineType.CONSECUTIVE_WORKING_DAYS, true, 3, 1, true, 2, 1));
        minMaxContractLines.add(createMinMaxContractLine(ContractLineType.CONSECUTIVE_FREE_DAYS, true, 2, 1, true, 1, 1));
        minMaxContractLines.add(createMinMaxContractLine(ContractLineType.CONSECUTIVE_WORKING_WEEKENDS, false, 0, 0, false, 0, 0));
        minMaxContractLines.add(createMinMaxContractLine(ContractLineType.TOTAL_WORKING_WEEKENDS_IN_FOUR_WEEKS, false, 0, 0, false, 0, 0));

        ec.executeChild(this, contractCreate.getCode(), contractCreate);
        contracts.add(contractCreate.getContract());
        contractLines = contractCreate.getContractLines();
    }

    private BooleanContractLine createBooleanContractLine(ContractLineType lineType, boolean enabled, int weight) {
//        Contract contract = contracts.get(0);
        BooleanContractLine contractLine = new BooleanContractLine();
//        contractLine.setContract(contract);
        contractLine.setContractLineType(lineType);
        contractLine.setEnabled(enabled);
        contractLine.setWeight(weight);
//        contract.getContractLineList().add(contractLine);
        return contractLine;
    }


    private MinMaxContractLine createMinMaxContractLine(ContractLineType lineType, boolean minimumEnabled, int minimumValue, int minimumWeight, boolean maximumEnabled, int maximumValue, int maximumWeight) {
//        Contract contract = contracts.get(0);
        MinMaxContractLine contractLine = new MinMaxContractLine();
//        contractLine.setContract(contract);
        contractLine.setContractLineType(lineType);

        contractLine.setMinimumEnabled(minimumEnabled);
        contractLine.setMinimumValue(minimumValue);
        contractLine.setMinimumWeight(minimumWeight);

        contractLine.setMaximumEnabled(maximumEnabled);
        contractLine.setMaximumValue(maximumValue);
        contractLine.setMaximumWeight(maximumWeight);

//        contract.getContractLineList().add(contractLine);
        return contractLine;
    }
}
