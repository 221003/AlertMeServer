package com.example.alertme.api.requests;

import java.util.Objects;

public class LoginRequestBody {
    private String login;
    private String password;

    public LoginRequestBody(String login, String password) {
        this.login = login;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginRequestBody)) return false;
        LoginRequestBody loginRequestBody = (LoginRequestBody) o;
        return Objects.equals(login, loginRequestBody.login) &&
                Objects.equals(password, loginRequestBody.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

