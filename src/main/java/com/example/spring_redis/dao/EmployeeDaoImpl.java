package com.example.spring_redis.dao;

import com.example.spring_redis.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {
    private final String KEY = "Employee";

    //'redisTemplate' is defined as a Bean in AppConfig.java
    @Resource(name="redisTemplate")
    public HashOperations<String, String, Employee> hashOps;

    @Override
    public Employee getEmployeeById(String id) {
        return hashOps.get(KEY, id);
    }

    @Override
    public void saveEmployee(Employee emp) {
        // create one record in Redis if record with Id is not present
        hashOps.putIfAbsent(KEY, emp.getId(), emp);
    }

    @Override
    public void updateEmployee(Employee emp) {
        hashOps.put(KEY, emp.getId(), emp);
    }

    @Override
    public String deleteEmployeeById(String id, String name) {
        hashOps.delete(KEY, id, name);
        return "Employee Records deleted!";
    }

    @Override
    public Map<String, Employee> getAllEmployees() {
        return hashOps.entries(KEY);
    }

    @Override
    public void saveAllEmployees(Map<String, Employee> map) {
        hashOps.putAll(KEY, map);
    }

    @Override
    public List<Employee> findAll() {
        return hashOps.values(KEY);
    }

}

