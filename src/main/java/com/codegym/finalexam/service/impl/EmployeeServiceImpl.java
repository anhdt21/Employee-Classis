package com.codegym.finalexam.service.impl;

import com.codegym.finalexam.model.Employee;
import com.codegym.finalexam.repository.EmployeeRepository;
import com.codegym.finalexam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findAllByNameContaining(Optional<String> s, Pageable pageable) {
        return employeeRepository.findAllByNameContaining(s, pageable);
    }
}
