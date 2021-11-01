package com.example.alertme.api.models;

import com.example.alertme.api.requests.NewAlertRequestBody;
import com.example.alertme.api.requests.NewAlertTypeRequestBody;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties("alerts")
public class AlertType {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String name;

    @OneToMany(mappedBy = "alertType", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("alertType")
    private List<Alert> alerts;

    public AlertType() {
    }

    public AlertType(Long id, String name, List<Alert> alerts) {
        this.id = id;
        this.name = name;
        this.alerts = alerts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof AlertType)) return false;
        AlertType alertType = (AlertType) o;
        return Objects.equals(id, alertType.id) && Objects.equals(name, alertType.name) && Objects.equals(alerts, alertType.alerts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, alerts);
    }

    @Override
    public String toString() {
        return "AlertType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alerts=" + alerts +
                '}';
    }

    public void setFromRequestBody(NewAlertTypeRequestBody newAlertType) {
        this.setName(newAlertType.getName());
    }

}
