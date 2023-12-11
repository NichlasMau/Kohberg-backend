package com.example.kohbergbackend.security.service;

import com.example.kohbergbackend.security.model.User;

import java.util.List;

public interface IUserService extends ICrudService<User,Long>{
    List<User> findByName(String name);
}
