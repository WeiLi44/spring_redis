package com.example.spring_redis.controller;

import com.example.spring_redis.model.Employee;
import com.example.spring_redis.dao.IEmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmpController{

    @Autowired
    IEmployeeDao dao;

    public EmpController() throws ParseException {
    }
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
    Date date = simpleFormat.parse("2022-03-18");


    @PostMapping("/add/{id}/{name}")
    public Employee add(@PathVariable("id") String id,
                        @PathVariable("name") String name){
        dao.saveEmployee(new Employee(id, name, "Account",6, date));
        return dao.getEmployeeById(id);
    }

    @PostMapping("/update/{id}/{name}")
    public Employee update(@PathVariable("id") String id,
                        @PathVariable("name") String name){
        dao.updateEmployee(new Employee(id, name, "Engineering", 8, date));
        return dao.getEmployeeById(id);
    }

    @GetMapping("/user/{id}")
    public Employee getEmployeeById(@PathVariable("id") String id){
        return dao.getEmployeeById(id);
    }

    @GetMapping("/all")
    public Map<String, Employee> getAll(){
        return dao.getAllEmployees();
    }

    @GetMapping("/list/all")
    public List<Employee> findAll(){
        return dao.findAll();
    }

    @GetMapping("/list/{years}/{dept}")
    public List<Employee> getNewList(@PathVariable("years") int years, @PathVariable("dept") String dept){
        List<Employee> myList = dao.findAll().stream()
                .filter(e -> e.getYears() == years && e.getDept().equals(dept))
                .map(e -> new Employee(e.getId(), e.getName(), e.getDept(), e.getYears(), e.getStartDate()))
                .collect(Collectors.toList());
        return myList;
    }

    @DeleteMapping("/delete/{id}/{name}")
    public void deleteEmployee(@PathVariable("id") String id, @PathVariable("name") String name){
        dao.deleteEmployeeById(id, name);
    }
}

