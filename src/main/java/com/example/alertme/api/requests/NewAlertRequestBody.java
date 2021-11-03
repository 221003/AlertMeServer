package com.example.alertme.api.requests;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class NewAlertRequestBody {
    private Long userId;
    private Long alertTypeId;
    private String title;
    private String description;
    private int number_of_votes;
    private double latitude;
    private double longitude;

    @Temporal(TemporalType.DATE)
    private Date expire_date;

    @Lob
    private byte[] image;

    public NewAlertRequestBody() {
    }

    public NewAlertRequestBody(Long userId, Long alertTypeId, String title, String description, int number_of_votes, double latitude, double longitude, Date expire_date, byte[] image) {
        this.userId = userId;
        this.alertTypeId = alertTypeId;
        this.title = title;
        this.description = description;
        this.number_of_votes = number_of_votes;
        this.latitude = latitude;
        this.longitude = longitude;
        this.expire_date = expire_date;
        this.image = image;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAlertTypeId() {
        return alertTypeId;
    }

    public void setAlertTypeId(Long alertTypeId) {
        this.alertTypeId = alertTypeId;
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

    public Date getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Date expire_date) {
        this.expire_date = expire_date;
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
        if (!(o instanceof NewAlertRequestBody)) return false;
        NewAlertRequestBody that = (NewAlertRequestBody) o;
        return number_of_votes == that.number_of_votes && latitude == that.latitude && longitude == that.longitude && Objects.equals(userId, that.userId) && Objects.equals(alertTypeId, that.alertTypeId) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(expire_date, that.expire_date) && Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userId, alertTypeId, title, description, number_of_votes, latitude, longitude, expire_date);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "NewAlertRequestBody{" +
                "userId=" + userId +
                ", alertTypeId=" + alertTypeId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", number_of_votes=" + number_of_votes +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", expire_date=" + expire_date +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
