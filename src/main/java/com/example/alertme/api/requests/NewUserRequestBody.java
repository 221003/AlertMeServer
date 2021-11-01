package com.example.alertme.api.requests;

import java.util.Objects;

public class NewUserRequestBody {
    private String first_name;
    private String last_name;
    private String login;
    private String password;
    private String email;

    public NewUserRequestBody() {
    }

    public NewUserRequestBody(String first_name, String last_name, String login, String password, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewUserRequestBody)) return false;
        NewUserRequestBody that = (NewUserRequestBody) o;
        return Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, login, password, email);
    }

    @Override
    public String toString() {
        return "NewUserRequestBody{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
