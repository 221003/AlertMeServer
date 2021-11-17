package com.example.alertme.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "alert_id")
    private Alert alert;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    private boolean is_upped;

    public Vote(){}

    public Vote(Alert alert, User user, boolean isUpped) {
        this.alert = alert;
        this.user = user;
        this.is_upped = isUpped;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUpped() {
        return is_upped;
    }

    public void setUpped(boolean upped) {
        is_upped = upped;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", alert=" + alert +
                ", user=" + user +
                ", is_upped=" + is_upped +
                '}';
    }
}
