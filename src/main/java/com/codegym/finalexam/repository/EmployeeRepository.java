package com.codegym.finalexam.repository;

import com.codegym.finalexam.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    Page<Employee> findAllByNameContaining(Optional<String> s, Pageable pageable);
}
