package com.example.kohbergbackend.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestModel  {
    private String username;
    private String password;
}
