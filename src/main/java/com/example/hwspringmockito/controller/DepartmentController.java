package com.example.hwspringmockito.controller;

import com.example.hwspringmockito.model.Employee;
import com.example.hwspringmockito.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    Collection<Employee> employees(@PathVariable("id") Integer id) {
        return departmentService.employees(id);
    }

    @GetMapping("/{id}/salary/sum")
    Integer sum(@PathVariable("id") Integer id) {
        return departmentService.sum(id);
    }

    @GetMapping("/{id}/salary/max")
    Integer max(@PathVariable("id") Integer id) {
        return departmentService.max(id);
    }

    @GetMapping("/{id}/salary/min")
    Integer min(@PathVariable("id") Integer id) {
        return departmentService.min(id);
    }

    @GetMapping("employees")
    Map<Integer, List<Employee>> employees() {
       return departmentService.groupsEmployeesByDepartment();
    }
}
