package com.nadajp.sample.demo.model;

public class RegistrationResponse {
    private String id;
    private String token;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
