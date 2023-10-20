package ru.unibell.clientinfoapi.exception;

public class ContactValidationException extends BaseException {

    public ContactValidationException(Error error) {
        super(error);
    }

}
