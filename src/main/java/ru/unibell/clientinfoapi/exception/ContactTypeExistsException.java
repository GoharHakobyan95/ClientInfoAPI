package ru.unibell.clientinfoapi.exception;

public class ContactTypeExistsException extends BaseException {
    public ContactTypeExistsException(Error error) {
        super(error);
    }
}
