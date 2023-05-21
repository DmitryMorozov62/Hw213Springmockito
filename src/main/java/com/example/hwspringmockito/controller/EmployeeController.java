package com.example.hwspringmockito.controller;

import com.example.hwspringmockito.model.Employee;
import com.example.hwspringmockito.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    Employee addEmployee(@RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam Integer salary,
                         @RequestParam Integer department) {
        return service.addEmployee(name, surname, salary, department);
    }

    @GetMapping("/find")
    Employee findEmployee(@RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam Integer salary,
                          @RequestParam Integer department) {
        return service.findEmployee(name, surname, salary, department);
    }

    @GetMapping("/remove")
    Employee removeEmployee(@RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam Integer salary,
                            @RequestParam Integer department) {
        return service.removeEmployee(name, surname, salary, department);
    }

    @GetMapping
    Collection<Employee> allEmployee() {
        return service.allEmployee();
    }
}
