package com.example.hwspringmockito.service;;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.hwspringmockito.exception.DepartmentNotFoundException;
import com.example.hwspringmockito.model.Employee;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    public static Stream<Arguments> maxSalaryFromDepartmentParams() {
        return Stream.of(
                Arguments.of(1, 15000),
                Arguments.of(2, 30000),
                Arguments.of(3, 20000)
        );
    }

    public static Stream<Arguments> minSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 10000),
                Arguments.of(2, 20000),
                Arguments.of(3, 20000)
        );
    }

    public static Stream<Arguments> sumSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 25000),
                Arguments.of(2, 50000),
                Arguments.of(3, 20000),
                Arguments.of(4, 0)
        );
    }

    public static Stream<Arguments> employeesFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                new Employee("Иван", "Иванов", 10000, 1),
                                new Employee("Пётр", "Петров", 15000, 1)
                        )
                ),
                Arguments.of(
                        2,
                        List.of(
                                new Employee("Мария", "Иванова", 20000, 2),
                                new Employee("Вася", "Пупкин", 30000, 2)
                        )
                ),
                Arguments.of(
                        3,
                        Collections.singletonList(new Employee("Анна", "Петрова", 20000, 3))
                ),
                Arguments.of(
                        4,
                        Collections.emptyList()
                )
        );
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.when(employeeService.allEmployee()).thenReturn(
                List.of(
                        new Employee("Иван", "Иванов", 10000, 1),
                        new Employee("Мария", "Иванова", 20000, 2),
                        new Employee("Пётр", "Петров", 15000, 1),
                        new Employee("Анна", "Петрова", 20000, 3),
                        new Employee("Вася", "Пупкин", 30000, 2)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("maxSalaryFromDepartmentParams")

    public void maxSalaryFromDepartmentTest(int departmentId, int expected) {
        Assertions.assertThat(departmentService.max(departmentId))
                .isEqualTo(expected);
    }

    @Test
    public void maxSalaryFromDepartmentWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.max(4));
    }

    @ParameterizedTest
    @MethodSource("minSalaryFromDepartmentTestParams")
    public void minSalaryFromDepartmentTest(int departmentId, int expected) {
        Assertions.assertThat(departmentService.min(departmentId))
                .isEqualTo(expected);
    }

    @Test
    public void minSalaryFromDepartmentWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.min(4));
    }

    @ParameterizedTest
    @MethodSource("sumSalaryFromDepartmentTestParams")
    public void sumSalaryFromDepartmentTest(int departmentId, int expected) {
        Assertions.assertThat(departmentService.sum(departmentId))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentTestParams")
    public void employeesFromDepartmentTest(int departmentId, List<Employee> expected) {
        Assertions.assertThat(departmentService.employees(departmentId))
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void employeesGroupedByDepartmentTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Иван", "Иванов", 10000, 1),
                        new Employee("Пётр", "Петров", 15000, 1)
                ),
                2,
                List.of(
                        new Employee("Мария", "Иванова", 20000, 2),
                        new Employee("Вася", "Пупкин", 30000, 2)
                ),
                3,
                Collections.singletonList(new Employee("Анна", "Петрова", 20000, 3))
        );
        Assertions.assertThat(departmentService.groupsEmployeesByDepartment())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }

}