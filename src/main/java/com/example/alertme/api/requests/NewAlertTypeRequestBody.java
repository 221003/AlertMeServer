package com.example.alertme.api.requests;

import java.util.Objects;

public class NewAlertTypeRequestBody {
    private String name;

    public NewAlertTypeRequestBody() {
    }

    public NewAlertTypeRequestBody(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewAlertTypeRequestBody)) return false;
        NewAlertTypeRequestBody that = (NewAlertTypeRequestBody) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "NewAlertTypeRequestBody{" +
                "name='" + name + '\'' +
                '}';
    }
}
