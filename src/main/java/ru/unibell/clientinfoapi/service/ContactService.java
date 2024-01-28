package ru.unibell.clientinfoapi.service;

import ru.unibell.clientinfoapi.models.dto.ContactDto;

public interface ContactService {

    ContactDto save(Long id, ContactDto contactDto);

}
