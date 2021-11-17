package com.example.alertme.api.requests;


import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteBody {

    @JsonProperty("alert_id")
    private Long alertId;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("is_upped")
    private boolean isUpped;

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isUpped() {
        return isUpped;
    }

    public void setUpped(boolean upped) {
        isUpped = upped;
    }

    @Override
    public String toString() {
        return "VoteBody{" +
                "alertId=" + alertId +
                ", userId=" + userId +
                ", isUpped=" + isUpped +
                '}';
    }
}
