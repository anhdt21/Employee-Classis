package com.codegym.finalexam;

import com.codegym.finalexam.service.EmployeeService;
import com.codegym.finalexam.service.GroupEmployeeService;
import com.codegym.finalexam.storage.StorageService;
import com.codegym.finalexam.service.impl.EmployeeServiceImpl;
import com.codegym.finalexam.service.impl.GroupEmployeeServiceImpl;
import com.codegym.finalexam.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FinalExamApplication {

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //storageService.deleteAll();
            storageService.init();
        };
    }

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
