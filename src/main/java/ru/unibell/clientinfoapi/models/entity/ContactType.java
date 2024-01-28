package ru.unibell.clientinfoapi.models.entity;

public enum ContactType {
    PHONE("PHONE"),
    EMAIL("EMAIL");

    private final String value;

    ContactType(String value) {
        this.value = value;
    }

}
