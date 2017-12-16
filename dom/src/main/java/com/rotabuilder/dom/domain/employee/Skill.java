package com.rotabuilder.dom.domain.employee;

import javax.jdo.annotations.IdentityType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.rotabuilder.dom.domain.solver.AbstractPersistable;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.isis.applib.annotation.DomainObject;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM Skill "
                        + "WHERE code.indexOf(:code) >= 0 ")
})
@DomainObject(
        autoCompleteRepository = EmployeeRepository.class,
        autoCompleteAction = "findSkillsByName")
@XStreamAlias("Skill")
public class Skill extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public int compareTo(Skill other) {
        return new CompareToBuilder()
                .append(this.getClass().getName(), other.getClass().getName())
                .append(code, other.code)
                .toComparison();
    }

}
