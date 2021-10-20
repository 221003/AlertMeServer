package com.example.alertme.api.controllers;

import java.util.Objects;

public class LoginForm {
    private String login;
    private String password;

    public LoginForm(String login, String password) {
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
        if (!(o instanceof LoginForm)) return false;
        LoginForm loginForm = (LoginForm) o;
        return Objects.equals(login, loginForm.login) &&
                Objects.equals(password, loginForm.password);
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

