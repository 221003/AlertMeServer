package com.example.alertme.api.models;

import com.example.alertme.api.exceptions.AlertTypeNotFoundException;
import com.example.alertme.api.exceptions.UserNotFoundException;
import com.example.alertme.api.repositories.AlertTypeRepository;
import com.example.alertme.api.repositories.UserRepository;
import com.example.alertme.api.requests.NewAlertRequestBody;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
public class Alert {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("alerts")
    private User user;

    @Temporal(TemporalType.DATE)
    private Date expire_date;

    @ManyToOne(targetEntity = AlertType.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "alert_type_id")
    @JsonIgnoreProperties("alerts")
    private AlertType alertType;

    private String title;
    private String description;
    private int number_of_votes;
    private double latitude;
    private double longitude;

    @Lob
    private byte[] image;

    public Alert() {
    }

    public Alert(Long id, User user, Date expire_date, AlertType alertType, String title, String description, int number_of_votes, double latitude, double longitude, byte[] image) {
        this.id = id;
        this.user = user;
        this.expire_date = expire_date;
        this.alertType = alertType;
        this.title = title;
        this.description = description;
        this.number_of_votes = number_of_votes;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Date expire_date) {
        this.expire_date = expire_date;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber_of_votes() {
        return number_of_votes;
    }

    public void setNumber_of_votes(int number_of_votes) {
        this.number_of_votes = number_of_votes;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alert)) return false;
        Alert alert = (Alert) o;
        return number_of_votes == alert.number_of_votes && latitude == alert.latitude && longitude == alert.longitude && Objects.equals(id, alert.id) && Objects.equals(user, alert.user) && Objects.equals(expire_date, alert.expire_date) && Objects.equals(alertType, alert.alertType) && Objects.equals(title, alert.title) && Objects.equals(description, alert.description) && Arrays.equals(image, alert.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, user, expire_date, alertType, title, description, number_of_votes, latitude, longitude);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", user=" + user +
                ", expire_date=" + expire_date +
                ", alertType=" + alertType +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", number_of_votes=" + number_of_votes +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    public void setFromRequestBody(UserRepository userRepository, AlertTypeRepository alertTypeRepository, NewAlertRequestBody newAlert) throws UserNotFoundException, AlertTypeNotFoundException {
        this.setTitle(newAlert.getTitle());
        this.setDescription(newAlert.getDescription());
        this.setLatitude(newAlert.getLatitude());
        this.setLongitude(newAlert.getLongitude());
        this.setExpire_date(newAlert.getExpire_date());
        this.setNumber_of_votes(newAlert.getNumber_of_votes());
        this.setImage(newAlert.getImage());

        User user = userRepository
                .findById(newAlert.getUserId())
                .orElseThrow(() -> new UserNotFoundException(newAlert.getUserId().toString()));
        this.setUser(user);

        AlertType alertType = alertTypeRepository
                .findById(newAlert.getAlertTypeId())
                .orElseThrow(() -> new AlertTypeNotFoundException(newAlert.getUserId().toString()));
        this.setAlertType(alertType);
    }

}
