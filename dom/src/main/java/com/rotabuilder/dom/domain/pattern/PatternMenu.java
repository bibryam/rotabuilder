package com.rotabuilder.dom.domain.pattern;

import java.util.List;

import com.rotabuilder.dom.domain.shift.ShiftType;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Pattern.class
)
@DomainServiceLayout(
        named = "Patterns",
        menuOrder = "40"
)
public class PatternMenu {
    public static class CreateDomainEvent extends ActionDomainEvent<PatternMenu> {}

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<Pattern> listPatterns() {
        return patternsRepository.listAll();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<FreeBefore2DaysWithAWorkDayPattern> listFreeBefore2DaysWithAWorkDayPatterns() {
        return patternsRepository.listFreeBefore2DaysWithAWorkDayPatterns();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "3")
    public List<ShiftType2DaysPattern> listShiftType2DaysPatterns() {
        return patternsRepository.listShiftType2DaysPatterns();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "4")
    public List<ShiftType3DaysPattern> listShiftType3DaysPatterns() {
        return patternsRepository.listShiftType3DaysPatterns();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "5")
    public List<WorkBeforeFreeSequencePattern> listWorkBeforeFreeSequencePatterns() {
        return patternsRepository.listWorkBeforeFreeSequencePatterns();
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "6")
    public FreeBefore2DaysWithAWorkDayPattern createFreeBefore2DaysWithAWorkDayPattern(
            @ParameterLayout(named = "Code")
            String code,
            @ParameterLayout(named = "Wights")
            int weight,
            @ParameterLayout(named = "dayOfWeek")
            DayOfWeek dayOfWeek) {
        return patternsRepository.createFreeBefore2DaysWithAWorkDayPattern(code, weight, dayOfWeek);
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "6")
    public ShiftType2DaysPattern createShiftType2DaysPattern(
            @ParameterLayout(named = "Code")
            String code,
            @ParameterLayout(named = "Wights")
            int weight,
            @ParameterLayout(named = "ShiftTypeOne")
            ShiftType shiftTypeOne,
            @ParameterLayout(named = "ShiftTypeTwo")
            ShiftType shiftTypeTwo) {
        return patternsRepository.createShiftType2DaysPattern(code, weight, shiftTypeOne, shiftTypeTwo);
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "7")
    public ShiftType3DaysPattern createShiftType3DaysPattern(
            @ParameterLayout(named = "Code")
            String code,
            @ParameterLayout(named = "Wights")
            int weight,
            @ParameterLayout(named = "ShiftTypeOne")
            ShiftType shiftTypeOne,
            @ParameterLayout(named = "ShiftTypeTwo")
            ShiftType shiftTypeTwo,
            @ParameterLayout(named = "ShiftTypeThree")
            ShiftType ShiftTypeThree) {
        return patternsRepository.createShiftType3DaysPattern(code, weight, shiftTypeOne, shiftTypeTwo, ShiftTypeThree);
    }


    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "8")
    public WorkBeforeFreeSequencePattern createWorkBeforeFreeSequencePattern(
            @ParameterLayout(named = "Code")
            String code,
            @ParameterLayout(named = "Wights")
            int weight,
            @ParameterLayout(named = "ShiftType")
            ShiftType shiftTypeOne,
            @ParameterLayout(named = "DayOfWeek")
            DayOfWeek dayOfWeek,
            @ParameterLayout(named = "FreeDayLength")
            int freeDayLength) {
        return patternsRepository.createWorkBeforeFreeSequencePattern(code, weight, shiftTypeOne, dayOfWeek, freeDayLength);
    }

    @javax.inject.Inject
    PatternsRepository patternsRepository;

}
