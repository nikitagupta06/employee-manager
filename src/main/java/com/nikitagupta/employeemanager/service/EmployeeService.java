package com.nikitagupta.employeemanager.service;

import com.nikitagupta.employeemanager.exception.UserNotFoundException;
import com.nikitagupta.employeemanager.model.Employee;
import com.nikitagupta.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);

    }
    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }
    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id).orElseThrow(
                () -> new UserNotFoundException("User by id " + id + " not found")
        );
    }
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
    public void removeEmployeeById(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }
}
