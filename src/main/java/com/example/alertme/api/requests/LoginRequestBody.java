package com.example.alertme.api.requests;

import java.util.Objects;

public class LoginRequestBody {
    private String login;
    private String password;
    private String token;

    public LoginRequestBody(String login, String password, String token) {
        this.login = login;
        this.password = password;
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginRequestBody)) return false;
        LoginRequestBody that = (LoginRequestBody) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(token, that.token);
    }

    @Override
    public String toString() {
        return "LoginRequestBody{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, token);
    }
}

