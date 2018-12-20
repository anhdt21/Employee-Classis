package com.codegym.finalexam.service;

import com.codegym.finalexam.model.GroupEmployee;

import java.util.Optional;

public interface GroupEmployeeService {
    Iterable<GroupEmployee> findAll();

    Optional<GroupEmployee> findById(Integer id);
}
