package com.rotabuilder.dom.domain.employee;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest {

    Employee employee;

    @Before
    public void setUp() throws Exception {
        employee = new Employee();
        employee.setName("test");
    }

    public static class Name extends EmployeeTest {

        @Test
        public void happyCase() throws Exception {
            // given
            String name = "test";
            assertThat(employee.getName()).isNotEmpty();

            // when
            employee.setName(name);

            // then
            assertThat(employee.getName()).isEqualTo(name);
        }
    }

}
