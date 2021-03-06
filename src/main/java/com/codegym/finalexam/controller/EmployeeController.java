package com.codegym.finalexam.controller;

import com.codegym.finalexam.model.Employee;
import com.codegym.finalexam.model.GroupEmployee;
import com.codegym.finalexam.service.EmployeeService;
import com.codegym.finalexam.service.GroupEmployeeService;
import com.codegym.finalexam.storage.StorageService;
import com.codegym.finalexam.storage.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Controller
public class EmployeeController {

    private final StorageService storageService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private GroupEmployeeService groupEmployeeService;

    public EmployeeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ModelAttribute("groupEmployees")
    public Iterable<GroupEmployee> groupEmployees() {
        return groupEmployeeService.findAll();
    }

    @GetMapping("/")
    public ModelAndView showList(@PageableDefault(size = 6)Pageable pageable) {
        Page<Employee> employees = employeeService.findAll(pageable);
        return new ModelAndView("views/index", "employees", employees);
    }

    @GetMapping("/search")
    public ModelAndView search(@PageableDefault(size = 6) @RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Employee> employees = employeeService.findAllByNameContaining(s, pageable);
        return new ModelAndView("views/index", "employees", employees);
    }

    @GetMapping("/create-employee")
    public ModelAndView showCreateEmployee() {
        return new ModelAndView("views/create", "employee", new Employee());
    }

    @PostMapping("/create-employee")
    public ModelAndView createNewEmployee(@Validated @ModelAttribute("employee") Employee employee,
                                          @RequestParam("file") MultipartFile file, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("views/create");
        } else {
            // file chua duoc chon thi khong xu ly va chuyen sang luu san pham
            if (!file.isEmpty()) {
                // lay datetime gan vao ten anh de khong bi trung ten
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                employee.setImage(timeStamp+file.getOriginalFilename());
                storageService.store(file);
            }
            employeeService.save(employee);
        }

        ModelAndView modelAndView = new ModelAndView("views/create", "employee", new Employee());
        modelAndView.addObject("message", "Created a Employee Successfully!");
        return modelAndView;
    }

    @GetMapping("/edit-employee/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Integer id) {
        return new ModelAndView("views/edit", "employee", employeeService.findById(id));
    }

    @PostMapping("/edit-employee")
    public ModelAndView updateEmployee(@Validated @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("views/edit");
        } else {
            employeeService.save(employee);
            ModelAndView modelAndView = new ModelAndView("views/edit", "employee", employee);
            modelAndView.addObject("message", "Updated successfully");
            return modelAndView;
        }
    }

    @GetMapping("/delete-employee/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Integer id) {
        return new ModelAndView("views/delete", "employee", employeeService.findById(id));
    }

    @PostMapping("/delete-employee")
    public ModelAndView deleteEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.delete(employee.getId());
        ModelAndView modelAndView = new ModelAndView("views/delete", "employee", employee);
        modelAndView.addObject("message", "Deleted Successfully");
        return modelAndView;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
