package com.rotabuilder.fixture.scenarios;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.annotation.Programmatic;

import com.rotabuilder.dom.domain.pattern.DayOfWeek;
import com.rotabuilder.dom.domain.pattern.FreeBefore2DaysWithAWorkDayPattern;
import com.rotabuilder.dom.domain.pattern.Pattern;
import com.rotabuilder.dom.domain.pattern.ShiftType2DaysPattern;
import com.rotabuilder.dom.domain.pattern.ShiftType3DaysPattern;
import com.rotabuilder.dom.domain.pattern.WorkBeforeFreeSequencePattern;
import com.rotabuilder.dom.domain.shift.ShiftMenu;
import com.rotabuilder.dom.domain.shift.ShiftType;
import com.rotabuilder.fixture.dom.PatternCreate;
import com.rotabuilder.fixture.dom.PatternTearDown;

public class RecreatePatterns extends MyFixtureScript {

    public RecreatePatterns() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    List<Pattern> patterns = new ArrayList<>();

    @Programmatic
    public List<Pattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    @Override
    protected void doExecute(final ExecutionContext ec) {

        ec.executeChild(this, new PatternTearDown());

        List<FreeBefore2DaysWithAWorkDayPattern> freeBefore2DaysWithAWorkDayPatterns = new ArrayList<>();
        List<ShiftType2DaysPattern> shiftType2DaysPatterns = new ArrayList<>();
        List<ShiftType3DaysPattern> shiftType3DaysPatterns = new ArrayList<>();
        List<WorkBeforeFreeSequencePattern> workBeforeFreeSequencePatterns = new ArrayList<>();

        PatternCreate patternCreate = new PatternCreate();
        patternCreate.setFreeBefore2DaysWithAWorkDayPatterns(freeBefore2DaysWithAWorkDayPatterns);
        patternCreate.setShiftType2DaysPatterns(shiftType2DaysPatterns);
        patternCreate.setShiftType3DaysPatterns(shiftType3DaysPatterns);
        patternCreate.setWorkBeforeFreeSequencePatterns(workBeforeFreeSequencePatterns);

        List<ShiftType> shiftTypes = shiftMenu.listShiftTypes();
        ShiftType early = shiftTypes.get(0);
        ShiftType late = shiftTypes.get(1);
        ShiftType night = shiftTypes.get(2);

        freeBefore2DaysWithAWorkDayPatterns.add(createFreeBefore2DaysWithAWorkDayPattern("0", 1, DayOfWeek.FRIDAY));
        freeBefore2DaysWithAWorkDayPatterns.add(createFreeBefore2DaysWithAWorkDayPattern("0", 1, DayOfWeek.SATURDAY));
        freeBefore2DaysWithAWorkDayPatterns.add(createFreeBefore2DaysWithAWorkDayPattern("0", 1, DayOfWeek.SUNDAY));
        shiftType2DaysPatterns.add(createShiftType2DaysPattern("1", 1, late, early));
        shiftType3DaysPatterns.add(createShiftType3DaysPattern("2", 1, early, late, night));
        workBeforeFreeSequencePatterns.add(createWorkBeforeFreeSequencePattern("3", 1, early, DayOfWeek.SUNDAY, 2));

        ec.executeChild(this, "pattern", patternCreate);
        patterns = patternCreate.getPatterns();
    }

    private FreeBefore2DaysWithAWorkDayPattern createFreeBefore2DaysWithAWorkDayPattern(String code, int weight, DayOfWeek dayOfWeek) {
        FreeBefore2DaysWithAWorkDayPattern pattern = new FreeBefore2DaysWithAWorkDayPattern();
        pattern.setCode(code);
        pattern.setWeight(weight);
        pattern.setFreeDayOfWeek(dayOfWeek);
        return pattern;
    }

    private ShiftType2DaysPattern createShiftType2DaysPattern(String code, int weight, ShiftType shiftTypeOne, ShiftType shiftTypeTwo) {
        ShiftType2DaysPattern pattern = new ShiftType2DaysPattern();
        pattern.setCode(code);
        pattern.setWeight(weight);
        pattern.setDayIndex0ShiftType(shiftTypeOne);
        pattern.setDayIndex1ShiftType(shiftTypeTwo);
        return pattern;
    }

    private ShiftType3DaysPattern createShiftType3DaysPattern(String code, int weight, ShiftType shiftTypeOne, ShiftType shiftTypeTwo, ShiftType shiftTypeThree) {
        ShiftType3DaysPattern pattern = new ShiftType3DaysPattern();
        pattern.setCode(code);
        pattern.setWeight(weight);
        pattern.setDayIndex0ShiftType(shiftTypeOne);
        pattern.setDayIndex1ShiftType(shiftTypeTwo);
        pattern.setDayIndex2ShiftType(shiftTypeThree);
        return pattern;
    }

    private WorkBeforeFreeSequencePattern createWorkBeforeFreeSequencePattern(String code, int weight, ShiftType shiftType, DayOfWeek workDayOfWeek, int freeDayLength) {
        WorkBeforeFreeSequencePattern pattern = new WorkBeforeFreeSequencePattern();
        pattern.setCode(code);
        pattern.setWeight(weight);
        pattern.setWorkShiftType(shiftType);
        pattern.setWorkDayOfWeek(workDayOfWeek);
        pattern.setFreeDayLength(freeDayLength);
        return pattern;
    }

    @javax.inject.Inject
    private ShiftMenu shiftMenu;
}
