package com.codegym.finalexam;

import com.codegym.finalexam.service.EmployeeService;
import com.codegym.finalexam.service.GroupEmployeeService;
import com.codegym.finalexam.service.impl.EmployeeServiceImpl;
import com.codegym.finalexam.service.impl.GroupEmployeeServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalExamApplication {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }

    @Bean
    public GroupEmployeeService groupEmployeeService() {
        return new GroupEmployeeServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalExamApplication.class, args);
    }
}
