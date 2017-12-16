package com.rotabuilder.dom.domain.pattern;

import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.InheritanceStrategy;

import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamInclude;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)

@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Discriminator(
        strategy = DiscriminatorStrategy.CLASS_NAME,
        column = "discriminator")

@XStreamAlias("Pattern")
@XStreamInclude({
        ShiftType2DaysPattern.class,
        ShiftType3DaysPattern.class,
        WorkBeforeFreeSequencePattern.class,
        FreeBefore2DaysWithAWorkDayPattern.class
})
public abstract class Pattern extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    protected String code;

    @javax.jdo.annotations.Column(allowsNull = "false")
    protected int weight;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return code;
    }

}
