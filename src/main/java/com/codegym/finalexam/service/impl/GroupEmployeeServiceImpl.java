package com.codegym.finalexam.service.impl;

import com.codegym.finalexam.model.GroupEmployee;
import com.codegym.finalexam.repository.GroupEmployeeRepository;
import com.codegym.finalexam.service.GroupEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class GroupEmployeeServiceImpl implements GroupEmployeeService {
    @Autowired
    GroupEmployeeRepository groupEmployeeRepository;

    @Override
    public Iterable<GroupEmployee> findAll() {
        return groupEmployeeRepository.findAll();
    }

    @Override
    public Optional<GroupEmployee> findById(Integer id) {
        return groupEmployeeRepository.findById(id);
    }
}
