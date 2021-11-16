package com.example.alertme.api.requests;

import java.util.Objects;

public class ChangePasswordRequest {
    private String old_password;
    private String new_password;

    public ChangePasswordRequest() {
    }

    public ChangePasswordRequest(String old_password, String new_password) {
        this.old_password = old_password;
        this.new_password = new_password;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChangePasswordRequest)) return false;
        ChangePasswordRequest that = (ChangePasswordRequest) o;
        return Objects.equals(old_password, that.old_password) && Objects.equals(new_password, that.new_password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(old_password, new_password);
    }

    @Override
    public String toString() {
        return "ChangePasswordRequest{" +
                "old_password='" + old_password + '\'' +
                ", new_password='" + new_password + '\'' +
                '}';
    }
}
