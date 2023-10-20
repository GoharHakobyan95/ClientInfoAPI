package ru.unibell.clientinfoapi.exception;

public class ClientNotFoundException extends BaseException {
    public ClientNotFoundException(Error error) {
        super(error);
    }
}
