package ru.unibell.clientinfoapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.unibell.clientinfoapi.models.dto.ContactDto;
import ru.unibell.clientinfoapi.models.entity.Client;
import ru.unibell.clientinfoapi.models.entity.Contact;
import ru.unibell.clientinfoapi.models.entity.ContactType;
import ru.unibell.clientinfoapi.exception.ContactTypeExistsException;
import ru.unibell.clientinfoapi.exception.ContactValidationException;
import ru.unibell.clientinfoapi.exception.Error;
import ru.unibell.clientinfoapi.mapper.ContactMapper;
import ru.unibell.clientinfoapi.repository.ContactRepository;
import ru.unibell.clientinfoapi.service.ContactService;
import ru.unibell.clientinfoapi.validator.ContactValidator;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor

public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ClientServiceImpl clientService;
    private final ContactMapper contactMapper;
    private final ContactValidator contactValidator;

    public ContactDto save(Long id, ContactDto contactDto) {
        Client client = clientService.getClientById(id);
        contactValidator.validateContact(contactDto.getContactType(), contactDto.getValue());
        Contact contact = createContactBasedOnType(contactDto, client);
        contactRepository.save(contact);
        return contactMapper.mapToDto(contact);
    }

    private Contact createContactBasedOnType(ContactDto contactDto, Client client) {
        ContactType contactType = contactDto.getContactType();
        return switch (contactType) {
            case PHONE -> contactMapper.toPhone(contactDto, client);
            case EMAIL -> contactMapper.toEmail(contactDto, client);
            default -> throw new ContactValidationException(Error.CONTACT_TYPE_ERR);
        };
    }
}
