package com.rotabuilder.integtests.modules;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.inject.Inject;

import com.google.common.base.Throwables;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.employee.EmployeeMenu;
import com.rotabuilder.fixture.dom.EmployeeTearDown;
import com.rotabuilder.fixture.scenarios.RecreateEmployees;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeMenu_IntegTest extends DomainAppIntegTest {

    @Inject
    FixtureScripts fixtureScripts;
    @Inject
    TransactionService transactionService;
    @Inject
    EmployeeMenu menu;

    public static class ListAll extends EmployeeMenu_IntegTest {

        @Test
        public void happyCase() throws Exception {

            // given
            RecreateEmployees fs = new RecreateEmployees();
            fixtureScripts.runFixtureScript(fs, null);
            transactionService.nextTransaction();

            // when
            final List<Employee> all = wrap(menu).listAll();

            // then
            assertThat(all).hasSize(fs.getEmployees().size());

            Employee employee = wrap(all.get(0));
            assertThat(employee.getName()).isEqualTo(fs.getEmployees().get(0).getName());
        }

        @Test
        public void whenNone() throws Exception {

            // given
            FixtureScript fs = new EmployeeTearDown();
            fixtureScripts.runFixtureScript(fs, null);
            transactionService.nextTransaction();

            // when
            final List<Employee> all = wrap(menu).listAll();

            // then
            assertThat(all).hasSize(0);
        }
    }

    public static class Create extends EmployeeMenu_IntegTest {

        @Test
        public void happyCase() throws Exception {

            // given
            FixtureScript fs = new EmployeeTearDown();
            fixtureScripts.runFixtureScript(fs, null);
            transactionService.nextTransaction();

            // when
            wrap(menu).create("Faz", "123", null);

            // then
            final List<Employee> all = wrap(menu).listAll();
            assertThat(all).hasSize(1);
        }

        @Test
        public void whenAlreadyExists() throws Exception {

            // given
            FixtureScript fs = new EmployeeTearDown();
            fixtureScripts.runFixtureScript(fs, null);
            transactionService.nextTransaction();
            wrap(menu).create("Faz", "123", null);
            transactionService.nextTransaction();

            // then
            expectedExceptions.expectCause(causalChainContains(SQLIntegrityConstraintViolationException.class));

            // when
            wrap(menu).create("Faz", "123", null);
            transactionService.nextTransaction();
        }

        private static Matcher<? extends Throwable> causalChainContains(final Class<?> cls) {
            return new TypeSafeMatcher<Throwable>() {
                @Override
                protected boolean matchesSafely(Throwable item) {
                    final List<Throwable> causalChain = Throwables.getCausalChain(item);
                    for (Throwable throwable : causalChain) {
                        if(cls.isAssignableFrom(throwable.getClass())){
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public void describeTo(Description description) {
                    description.appendText("exception with causal chain containing " + cls.getSimpleName());
                }
            };
        }
    }

}