package com.example.hwspringmockito.service;

import com.example.hwspringmockito.exception.DepartmentNotFoundException;
import com.example.hwspringmockito.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class DepartmentService{
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> employees(Integer department) {
        return employeeService.allEmployee().stream()
                .filter(e -> e.getDepartment()==department)
                .collect(Collectors.toList());
    }

    public int sum(Integer department) {
        return employeeService.allEmployee().stream()
                .filter(e->e.getDepartment()==department)
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    public int max(Integer department) throws DepartmentNotFoundException{
        return employeeService.allEmployee().stream()
                .filter(e -> e.getDepartment()==department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public int min(Integer department) {
        return employeeService.allEmployee().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Map<Integer, List<Employee>> groupsEmployeesByDepartment() {
        return employeeService.allEmployee().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
