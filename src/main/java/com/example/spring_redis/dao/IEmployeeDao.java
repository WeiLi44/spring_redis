package com.example.spring_redis.dao;

import com.example.spring_redis.model.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeDao {

    Employee getEmployeeById(String id);
    void saveEmployee(Employee emp);
    void updateEmployee(Employee emp);
    String deleteEmployeeById(String id, String name);
    Map<String, Employee> getAllEmployees();
    void saveAllEmployees(Map<String, Employee> map);
    List<Employee> findAll();
}

