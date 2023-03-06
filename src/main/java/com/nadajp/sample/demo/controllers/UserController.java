package com.nadajp.sample.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import com.nadajp.sample.demo.model.Login;
import com.nadajp.sample.demo.model.RegistrationResponse;
import com.nadajp.sample.demo.model.UserResponse;
import com.nadajp.sample.exceptions.InvalidLoginException;
import com.nadajp.sample.exceptions.UnsuccessfulRegistrationException;

@RestController
@RequestMapping(value = "/", produces = "application/json")
@CrossOrigin(origins = { "http://localhost:4200", "https://frontend.nadajp.repl.co" })
public class UserController {

  @Value("${reqresUrl}")
  private String reqresUrl;

  @Autowired
  private RestTemplate restTemplate;

  @PostMapping("register")
  public RegistrationResponse register(@RequestBody Login request) throws UnsuccessfulRegistrationException {
    String url = reqresUrl + "register";

    ResponseEntity<RegistrationResponse> entity;
    try {
      entity = restTemplate.postForEntity(url, request, RegistrationResponse.class);
    } catch (Exception e) {
      throw new UnsuccessfulRegistrationException(e.getMessage());
    }
    return entity.getStatusCode() == HttpStatus.OK ? entity.getBody() : null;
  }

  @PostMapping("login")
  public String login(@RequestBody Login request) throws InvalidLoginException {
    String url = reqresUrl + "login";

    ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);

    HttpStatusCode statusCode = entity.getStatusCode();

    if (statusCode == HttpStatusCode.valueOf(400)) {
      throw new InvalidLoginException(entity.getBody());
    }
    String token = entity.getBody();
    return token;
  }

  @GetMapping("users/{id}")
  public UserResponse getUserById(@PathVariable long id) {
    String url = reqresUrl + "users/" + id;

    ResponseEntity<UserResponse> entity = restTemplate.getForEntity(url, UserResponse.class);
    UserResponse body = entity.getBody();
    return body;
  }
}
