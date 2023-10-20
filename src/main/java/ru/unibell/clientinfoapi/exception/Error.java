package ru.unibell.clientinfoapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Error {

    CLIENT_NOT_FOUND(404, HttpStatus.NOT_FOUND, "No client found with the provided ID."),
    CONTACT_TYPE_ERR(404, HttpStatus.NOT_FOUND, "The contact type is not valid."),
    CONTACT_EXISTS_ERR(400, HttpStatus.BAD_REQUEST, "The contact already exists.");

    private final Integer code;
    private final HttpStatus status;
    private final String message;
}
