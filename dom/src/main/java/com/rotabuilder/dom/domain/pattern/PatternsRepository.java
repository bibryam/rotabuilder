package com.rotabuilder.dom.domain.pattern;

import java.util.List;

import com.rotabuilder.dom.domain.shift.ShiftType;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Pattern.class
)
public class PatternsRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<Pattern> listAll() {
        return repositoryService.allInstances(Pattern.class);
    }

    public List<FreeBefore2DaysWithAWorkDayPattern> listFreeBefore2DaysWithAWorkDayPatterns() {
        return repositoryService.allInstances(FreeBefore2DaysWithAWorkDayPattern.class);
    }

    public List<ShiftType2DaysPattern> listShiftType2DaysPatterns() {
        return repositoryService.allInstances(ShiftType2DaysPattern.class);
    }

    public List<ShiftType3DaysPattern> listShiftType3DaysPatterns() {
        return repositoryService.allInstances(ShiftType3DaysPattern.class);
    }

    public List<WorkBeforeFreeSequencePattern> listWorkBeforeFreeSequencePatterns() {
        return repositoryService.allInstances(WorkBeforeFreeSequencePattern.class);
    }

    public FreeBefore2DaysWithAWorkDayPattern createFreeBefore2DaysWithAWorkDayPattern(String code, int weight, DayOfWeek dayOfWeek) {
        final FreeBefore2DaysWithAWorkDayPattern object = new FreeBefore2DaysWithAWorkDayPattern();
        object.setCode(code);
        object.setWeight(weight);
        object.setFreeDayOfWeek(dayOfWeek);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public ShiftType2DaysPattern createShiftType2DaysPattern(String code, int weight, ShiftType shiftTypeOne, ShiftType shiftTypeTwo) {
        final ShiftType2DaysPattern object = new ShiftType2DaysPattern();
        object.setCode(code);
        object.setWeight(weight);
        object.setDayIndex0ShiftType(shiftTypeOne);
        object.setDayIndex1ShiftType(shiftTypeTwo);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public ShiftType3DaysPattern createShiftType3DaysPattern(String code, int weight, ShiftType shiftTypeOne, ShiftType shiftTypeTwo, ShiftType shiftTypeThree) {
        final ShiftType3DaysPattern object = new ShiftType3DaysPattern();
        object.setCode(code);
        object.setWeight(weight);
        object.setDayIndex0ShiftType(shiftTypeOne);
        object.setDayIndex1ShiftType(shiftTypeTwo);
        object.setDayIndex2ShiftType(shiftTypeThree);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public WorkBeforeFreeSequencePattern createWorkBeforeFreeSequencePattern(String code, int weight, ShiftType shiftType, DayOfWeek dayOfWeek, int freeDayLength) {
        final WorkBeforeFreeSequencePattern object = new WorkBeforeFreeSequencePattern();
        object.setCode(code);
        object.setWeight(weight);
        object.setWorkShiftType(shiftType);
        object.setWorkDayOfWeek(dayOfWeek);
        object.setFreeDayLength(freeDayLength);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

}
