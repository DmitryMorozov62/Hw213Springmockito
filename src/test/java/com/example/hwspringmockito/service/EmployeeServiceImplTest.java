package com.example.hwspringmockito.service;

import com.example.hwspringmockito.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.hwspringmockito.exception.EmployeeAlreadyAddedException;
import com.example.hwspringmockito.exception.EmployeeBadRequestException;
import com.example.hwspringmockito.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@SpringBootTest
class EmployeeServiceImplTest {

    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    void addEmployeeTest_name_null() {
        Assertions.assertThrows(EmployeeBadRequestException.class, () -> employeeService.addEmployee(null, "Петров", 25000, 1));
    }

    @Test
    void addEmployeeTest_surname_null() {
        Assertions.assertThrows(EmployeeBadRequestException.class, () -> employeeService.addEmployee("Виктор", null, 25000, 1));
    }

    @Test
    void addEmployeeTest_name_bedParam() {
        Assertions.assertThrows(EmployeeBadRequestException.class, () -> employeeService.addEmployee("Вик1№тор", "петров", 25000, 1));
    }

    @Test
    void addEmployeeTest_surname_bedParam() {
        Assertions.assertThrows(EmployeeBadRequestException.class, () -> employeeService.addEmployee("Виктор", "32ветерков", 25000, 1));
    }

    @Test
    void adding_the_same_object_test() {
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                () -> {
                    employeeService.addEmployee("Иван", "Иванов", 55000, 1);
                    employeeService.addEmployee("Иван", "Иванов", 55000, 1);
                });
    }

    @Test
    void object_not_found_test() {
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> {
                    new Employee("Петр", "Петров", 10000, 3);
                    employeeService.findEmployee("Иван", "Иванов", 55000, 1);
                });
    }

    public static Stream<Arguments> provideEmployeesByTest() {
        return Stream.of(
                Arguments.of(new Employee("Иван", "Иванов", 55000, 1), "Иван", "Иванов", 55000, 1),
                Arguments.of(new Employee("Петр", "Петров", 10000, 3), "ПЕТР", "пеТРОв", 10000, 3)
        );
    }


    @ParameterizedTest
    @MethodSource("provideEmployeesByTest")
    void addEmployeesTest(Employee result, String name, String surname, int salary, int department) {
        Assertions.assertEquals(result, employeeService.addEmployee(name, surname, salary, department));
    }

    @ParameterizedTest
    @MethodSource("provideEmployeesByTest")
    void findEmployeesTest(Employee result, String name, String surname, int salary, int department) {
        Assertions.assertEquals(result, employeeService.addEmployee(name, surname, salary, department));
    }


    @ParameterizedTest
    @MethodSource("provideEmployeesByTest")
    void removeEmployeesTest(Employee result, String name, String surname, int salary, int department) {
        Assertions.assertEquals(result, employeeService.addEmployee(name, surname, salary, department));
    }

}
