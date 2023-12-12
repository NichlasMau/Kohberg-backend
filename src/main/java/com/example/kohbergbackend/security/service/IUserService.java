package com.example.kohbergbackend.security.service;

import com.example.kohbergbackend.security.model.User2;

import java.util.List;

public interface IUserService extends ICrudService<User2,Long>{
    List<User2> findByName(String name);
}
