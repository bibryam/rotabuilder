package com.rotabuilder.dom.domain.shift;

import java.util.Set;
import java.util.TreeSet;
import javax.jdo.annotations.IdentityType;

import com.rotabuilder.dom.domain.employee.Skill;
import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.SemanticsOf;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@DomainObject(
        autoCompleteRepository = ShiftRepository.class,
        autoCompleteAction = "autoComplete")
@XStreamAlias("ShiftType")
public class ShiftType extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String code;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private int index;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String startTimeString;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String endTimeString;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private boolean night;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String description;

    @javax.jdo.annotations.Persistent(table = "ShiftTypeSkills")
    @javax.jdo.annotations.Join()
    private Set<Skill> skills = new TreeSet<>();

    public Set<Skill> getSkills() {
        return skills;
    }
    @Collection()
    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Action
    public ShiftType addSkill(Skill skill) {
        getSkills().add(skill);
        return this;
    }

    @Action(
            semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE
    )
    public ShiftType removeSkill(Skill skill) {
        getSkills().remove(skill);
        return this;
    }

    @Action(
            semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE
    )
    public void complete() {
        System.out.println("sss");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public boolean isNight() {
        return night;
    }

    public void setNight(boolean night) {
        this.night = night;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return code + " (" + description + ")";
    }

    @Override
    public String toString() {
        return code;
    }

}
