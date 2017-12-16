package com.rotabuilder.fixture.dom;

import java.util.ArrayList;
import java.util.List;

import com.rotabuilder.dom.domain.pattern.FreeBefore2DaysWithAWorkDayPattern;
import com.rotabuilder.dom.domain.pattern.Pattern;
import com.rotabuilder.dom.domain.pattern.PatternsRepository;
import com.rotabuilder.dom.domain.pattern.ShiftType2DaysPattern;
import com.rotabuilder.dom.domain.pattern.ShiftType3DaysPattern;
import com.rotabuilder.dom.domain.pattern.WorkBeforeFreeSequencePattern;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class PatternCreate extends FixtureScript {

    private List<FreeBefore2DaysWithAWorkDayPattern> freeBefore2DaysWithAWorkDayPatterns = new ArrayList<>();
    private List<ShiftType2DaysPattern> shiftType2DaysPatterns = new ArrayList<>();
    private List<ShiftType3DaysPattern> shiftType3DaysPatterns = new ArrayList<>();
    private List<WorkBeforeFreeSequencePattern> workBeforeFreeSequencePatterns = new ArrayList<>();
    private List<Pattern> patterns = new ArrayList<>();

    public List<FreeBefore2DaysWithAWorkDayPattern> getFreeBefore2DaysWithAWorkDayPatterns() {
        return freeBefore2DaysWithAWorkDayPatterns;
    }

    public void setFreeBefore2DaysWithAWorkDayPatterns(List<FreeBefore2DaysWithAWorkDayPattern> freeBefore2DaysWithAWorkDayPatterns) {
        this.freeBefore2DaysWithAWorkDayPatterns = freeBefore2DaysWithAWorkDayPatterns;
    }

    public List<ShiftType2DaysPattern> getShiftType2DaysPatterns() {
        return shiftType2DaysPatterns;
    }

    public void setShiftType2DaysPatterns(List<ShiftType2DaysPattern> shiftType2DaysPatterns) {
        this.shiftType2DaysPatterns = shiftType2DaysPatterns;
    }

    public List<ShiftType3DaysPattern> getShiftType3DaysPatterns() {
        return shiftType3DaysPatterns;
    }

    public void setShiftType3DaysPatterns(List<ShiftType3DaysPattern> shiftType3DaysPatterns) {
        this.shiftType3DaysPatterns = shiftType3DaysPatterns;
    }

    public List<WorkBeforeFreeSequencePattern> getWorkBeforeFreeSequencePatterns() {
        return workBeforeFreeSequencePatterns;
    }

    public void setWorkBeforeFreeSequencePatterns(List<WorkBeforeFreeSequencePattern> workBeforeFreeSequencePatterns) {
        this.workBeforeFreeSequencePatterns = workBeforeFreeSequencePatterns;
    }

    public PatternsRepository getPatternsRepository() {
        return patternsRepository;
    }

    public void setPatternsRepository(PatternsRepository patternsRepository) {
        this.patternsRepository = patternsRepository;
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    @Override
    protected void execute(final ExecutionContext ec) {
        List<FreeBefore2DaysWithAWorkDayPattern> freeBefore2DaysWithAWorkDayPatterns = checkParam("freeBefore2DaysWithAWorkDayPatterns", ec, List.class);
        List<ShiftType2DaysPattern> shiftType2DaysPatterns = checkParam("shiftType2DaysPatterns", ec, List.class);
        List<ShiftType3DaysPattern> shiftType3DaysPatterns = checkParam("shiftType3DaysPatterns", ec, List.class);
        List<WorkBeforeFreeSequencePattern> workBeforeFreeSequencePatterns = checkParam("workBeforeFreeSequencePatterns", ec, List.class);

        for (FreeBefore2DaysWithAWorkDayPattern pattern : freeBefore2DaysWithAWorkDayPatterns) {
            FreeBefore2DaysWithAWorkDayPattern createdPattern = wrap(patternsRepository).createFreeBefore2DaysWithAWorkDayPattern(
                    pattern.getCode(), pattern.getWeight(), pattern.getFreeDayOfWeek());
            ec.addResult(this, createdPattern);
            patterns.add(createdPattern);
        }

        for (ShiftType2DaysPattern pattern : shiftType2DaysPatterns) {
            ShiftType2DaysPattern createdPattern = wrap(patternsRepository).createShiftType2DaysPattern(
                    pattern.getCode(), pattern.getWeight(), pattern.getDayIndex0ShiftType(), pattern.getDayIndex1ShiftType());
            ec.addResult(this, createdPattern);
            patterns.add(createdPattern);
        }

        for (ShiftType3DaysPattern pattern : shiftType3DaysPatterns) {
            ShiftType3DaysPattern createdPattern = wrap(patternsRepository).createShiftType3DaysPattern(
                    pattern.getCode(), pattern.getWeight(), pattern.getDayIndex0ShiftType(), pattern.getDayIndex1ShiftType(), pattern.getDayIndex2ShiftType());
            ec.addResult(this, createdPattern);
            patterns.add(createdPattern);
        }

        for (WorkBeforeFreeSequencePattern pattern : workBeforeFreeSequencePatterns) {
            WorkBeforeFreeSequencePattern createdPattern = wrap(patternsRepository).createWorkBeforeFreeSequencePattern(
                    pattern.getCode(), pattern.getWeight(), pattern.getWorkShiftType(), pattern.getWorkDayOfWeek(), pattern.getFreeDayLength());
            ec.addResult(this, createdPattern);
            patterns.add(createdPattern);
        }
    }

    @javax.inject.Inject
    private PatternsRepository patternsRepository;

}
