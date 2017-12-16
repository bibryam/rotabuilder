package com.rotabuilder.integtests.modules;

import java.sql.Timestamp;
import javax.inject.Inject;

import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.fixture.scenarios.RecreateEmployees;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.services.wrapper.DisabledException;
import org.apache.isis.applib.services.wrapper.InvalidException;
import org.apache.isis.applib.services.xactn.TransactionService;
import org.apache.isis.core.metamodel.services.jdosupport.Persistable_datanucleusIdLong;
import org.apache.isis.core.metamodel.services.jdosupport.Persistable_datanucleusVersionTimestamp;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Employee_IntegTest extends DomainAppIntegTest {

    @Inject
    FixtureScripts fixtureScripts;
    @Inject
    TransactionService transactionService;

    Employee employee;

    @Before
    public void setUp() throws Exception {
        // given
        RecreateEmployees fs = new RecreateEmployees().setNumber(1);
        fixtureScripts.runFixtureScript(fs, null);
        transactionService.nextTransaction();

        employee = fs.getEmployees().get(0);

        assertThat(employee).isNotNull();
    }

    public static class Name extends Employee_IntegTest {

        @Test
        public void accessible() throws Exception {
            // when
            final String name = wrap(employee).getName();

            // then
            assertThat(name).isEqualTo(employee.getName());
        }

        @Test
        public void not_editable() throws Exception {
            // expect
            expectedExceptions.expect(DisabledException.class);

            // when
            wrap(employee).setName("new name");
        }

    }

    public static class UpdateName extends Employee_IntegTest {

        @Test
        public void can_be_updated_directly() throws Exception {

            // when
//            wrap(employee).updateName("new name");
            transactionService.nextTransaction();

            // then
            assertThat(wrap(employee).getName()).isEqualTo("new name");
        }

        @Test
        public void failsValidation() throws Exception {

            // expect
            expectedExceptions.expect(InvalidException.class);
            expectedExceptions.expectMessage("Exclamation mark is not allowed");

            // when
//            wrap(employee).updateName("new name!");
        }
    }


    public static class Title extends Employee_IntegTest {

        @Inject
        TitleService titleService;

        @Test
        public void interpolatesName() throws Exception {

            // given
            final String name = wrap(employee).getName();

            // when
            final String title = titleService.titleOf(employee);

            // then
            assertThat(title).isEqualTo("Object: " + name);
        }
    }

    public static class DataNucleusId extends Employee_IntegTest {

        @Test
        public void should_be_populated() throws Exception {
            // when
            final Long id = mixin(Persistable_datanucleusIdLong.class, employee).$$();

            // then
            assertThat(id).isGreaterThanOrEqualTo(0);
        }
    }

    public static class DataNucleusVersionTimestamp extends Employee_IntegTest {

        @Test
        public void should_be_populated() throws Exception {
            // when
            final Timestamp timestamp = mixin(Persistable_datanucleusVersionTimestamp.class, employee).$$();
            // then
            assertThat(timestamp).isNotNull();
        }
    }


}