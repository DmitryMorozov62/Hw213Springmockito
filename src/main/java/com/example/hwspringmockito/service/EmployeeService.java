package com.example.hwspringmockito.service;

import com.example.hwspringmockito.exception.EmployeeAlreadyAddedException;
import com.example.hwspringmockito.exception.EmployeeBadRequestException;
import com.example.hwspringmockito.exception.EmployeeNotFoundException;
import com.example.hwspringmockito.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Repository
public class EmployeeService {
    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    @PostConstruct
    void init() {
        employees.add(new Employee("Вася", "Петров", 35000, 1));
        employees.add(new Employee("Алексей", "Летов", 55000, 3));
        employees.add(new Employee("Иван", "Иванов", 40000, 2));
        employees.add(new Employee("Виктор", "Сидоров", 66000, 3));
        employees.add(new Employee("Владимир", "Быстров", 90000, 3));
    }

    public Employee addEmployee(String name, String lastname, int salary, int department) {
        Employee employee = new Employee(name, lastname, salary, department);
        nameValidate(name,lastname);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    public Employee findEmployee( String name, String lastname, int salary, int department) {
        Employee employee = new Employee(name, lastname, salary, department);
        nameValidate(name,lastname);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee removeEmployee(String name, String lastname, int salary, int department) {
        Employee employee = new Employee(name, lastname, salary, department);
        nameValidate(name,lastname);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> allEmployee() {
        return Collections.unmodifiableList(employees);
    }

    public void nameValidate(String name, String surname) {
        if (!StringUtils.isAlpha(name) || !StringUtils.isAlpha(surname)) {
            throw new EmployeeBadRequestException();
        }
    }
}
