package com.rotabuilder.dom.domain.employee;

import java.util.List;

import com.google.common.collect.Lists;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2.Mode;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeRepositoryTest {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(Mode.INTERFACES_AND_CLASSES);

    @Mock
    ServiceRegistry2 mockServiceRegistry;
    
    @Mock
    RepositoryService mockRepositoryService;

    EmployeeRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new EmployeeRepository();
        repository.repositoryService = mockRepositoryService;
        repository.serviceRegistry = mockServiceRegistry;
    }

    public static class Create extends EmployeeRepositoryTest {

        @Test
        @Ignore
        public void happyCase() throws Exception {

            final String someName = "Foobar";
            final String code = "code";

            // given
            final Sequence seq = context.sequence("create");
            context.checking(new Expectations() {
                {
                    oneOf(mockServiceRegistry).injectServicesInto(with(any(Employee.class)));
                    inSequence(seq);

                    oneOf(mockRepositoryService).persist(with(nameOf(someName)));
                    inSequence(seq);
                }

            });

            // when
            final Employee obj = repository.create(someName, code, null);

            // then
            assertThat(obj).isNotNull();
            assertThat(obj.getName()).isEqualTo(someName);
        }

        private static Matcher<Employee> nameOf(final String name) {
            return new TypeSafeMatcher<Employee>() {
                @Override
                protected boolean matchesSafely(final Employee item) {
                    return name.equals(item.getName());
                }

                @Override
                public void describeTo(final Description description) {
                    description.appendText("has name of '" + name + "'");
                }
            };
        }
    }

    public static class ListAll extends EmployeeRepositoryTest {

        @Test
        public void happyCase() throws Exception {

            // given
            final List<Employee> all = Lists.newArrayList();

            context.checking(new Expectations() {
                {
                    oneOf(mockRepositoryService).allInstances(Employee.class);
                    will(returnValue(all));
                }
            });

            // when
            final List<Employee> list = repository.listAll();

            // then
            assertThat(list).isEqualTo(all);
        }
    }
}
