package com.example.alertme.api.requests;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

public class NewAlertRequestBody {
    private Long userId;
    private Long alertTypeId;
    private String title;
    private String description;
    private int number_of_votes;
    private int coordinate_x;
    private int coordinate_y;

    @Temporal(TemporalType.DATE)
    private Date expire_date;

    public NewAlertRequestBody() {
    }

    public NewAlertRequestBody(Long userId, Long alertTypeId, String title, String description, int number_of_votes, int coordinate_x, int coordinate_y, Date expire_date) {
        this.userId = userId;
        this.alertTypeId = alertTypeId;
        this.title = title;
        this.description = description;
        this.number_of_votes = number_of_votes;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.expire_date = expire_date;
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

    public int getCoordinate_x() {
        return coordinate_x;
    }

    public void setCoordinate_x(int coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public int getCoordinate_y() {
        return coordinate_y;
    }

    public void setCoordinate_y(int coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    public Date getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Date expire_date) {
        this.expire_date = expire_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewAlertRequestBody)) return false;
        NewAlertRequestBody that = (NewAlertRequestBody) o;
        return number_of_votes == that.number_of_votes && coordinate_x == that.coordinate_x && coordinate_y == that.coordinate_y && Objects.equals(userId, that.userId) && Objects.equals(alertTypeId, that.alertTypeId) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(expire_date, that.expire_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, alertTypeId, title, description, number_of_votes, coordinate_x, coordinate_y, expire_date);
    }

    @Override
    public String toString() {
        return "NewAlertRequestBody{" +
                "userId=" + userId +
                ", alertTypeId=" + alertTypeId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", number_of_votes=" + number_of_votes +
                ", coordinate_x=" + coordinate_x +
                ", coordinate_y=" + coordinate_y +
                ", expire_date=" + expire_date +
                '}';
    }
}
