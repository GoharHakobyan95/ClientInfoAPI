package ru.unibell.clientinfoapi.service;

import ru.unibell.clientinfoapi.dto.ContactCreateDto;

public interface ContactService {

    ContactCreateDto save(Long id, ContactCreateDto contactCreateDto);

}
