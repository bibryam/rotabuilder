package com.rotabuilder.dom.domain.pattern;

import javax.jdo.annotations.InheritanceStrategy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@javax.jdo.annotations.PersistenceCapable
@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)

@XStreamAlias("FreeBefore2DaysWithAWorkDayPattern")
public class FreeBefore2DaysWithAWorkDayPattern extends Pattern {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private DayOfWeek freeDayOfWeek;

    public DayOfWeek getFreeDayOfWeek() {
        return freeDayOfWeek;
    }

    public void setFreeDayOfWeek(DayOfWeek freeDayOfWeek) {
        this.freeDayOfWeek = freeDayOfWeek;
    }

    @Override
    public String toString() {
        return "Free on " + freeDayOfWeek + " followed by a work day within 2 days";
    }

}
