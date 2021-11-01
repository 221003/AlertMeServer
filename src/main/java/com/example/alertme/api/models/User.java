package com.example.alertme.api.models;

import com.example.alertme.api.requests.NewAlertTypeRequestBody;
import com.example.alertme.api.requests.NewUserRequestBody;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="\"AppUsers\"")
public class User {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String first_name;
    private String last_name;
    private String login;
    private String password_hash;
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("user")
    private List<Alert> alerts;

    public User() {
    }

    public User(Long id, String first_name, String last_name, String login, String password_hash, String email, List<Alert> alerts) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password_hash = password_hash;
        this.email = email;
        this.alerts = alerts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(login, user.login) && Objects.equals(password_hash, user.password_hash) && Objects.equals(email, user.email) && Objects.equals(alerts, user.alerts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, login, password_hash, email, alerts);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", login='" + login + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", email='" + email + '\'' +
                ", alerts=" + alerts +
                '}';
    }

    public void setFromRequestBody(NewUserRequestBody newUser) {
        this.setLast_name(newUser.getLast_name());
        this.setFirst_name(newUser.getFirst_name());
        this.setLogin(newUser.getLogin());
        this.setEmail(newUser.getEmail());
    }
}
