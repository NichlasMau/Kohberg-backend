package com.example.kohbergbackend.security.service;

import com.example.kohbergbackend.security.model.Employee;

import java.util.List;

public interface IUserService extends ICrudService<Employee,Long>{
    List<Employee> findByName(String name);
}
