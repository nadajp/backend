package com.nadajp.sample.demo.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class User {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
    private String password;
}
