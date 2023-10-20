package ru.unibell.clientinfoapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.unibell.clientinfoapi.dto.ContactCreateDto;
import ru.unibell.clientinfoapi.entity.Client;
import ru.unibell.clientinfoapi.entity.Contact;
import ru.unibell.clientinfoapi.entity.ContactType;
import ru.unibell.clientinfoapi.exception.ContactTypeExistsException;
import ru.unibell.clientinfoapi.exception.ContactValidationException;
import ru.unibell.clientinfoapi.exception.Error;
import ru.unibell.clientinfoapi.mapper.ContactMapper;
import ru.unibell.clientinfoapi.repository.ContactRepository;
import ru.unibell.clientinfoapi.service.ContactService;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor

public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ClientServiceImpl clientService;
    private final ContactMapper contactMapper;

    private static final String PHONE_REGEX = "^(?:\\+7|7|8)[\\s-]?(\\d{3})[\\s-]?(\\d{2})[\\s-]?(\\d{2})$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    public ContactCreateDto save(Long id, ContactCreateDto contactCreateDto) {
        Client client = clientService.getClientById(id);
        Contact contact = createContactBasedOnType(contactCreateDto, client);
        contactRepository.save(contact);
        return contactMapper.mapToDto(contact);
    }


    private Contact createContactBasedOnType(ContactCreateDto contactCreateDto, Client client) {
        ContactType contactType = contactCreateDto.getContactType();
        String value = contactCreateDto.getValue();
        validateContactValue(contactType, value);
        return switch (contactType) {
            case PHONE -> contactMapper.toPhone(contactCreateDto, client);
            case EMAIL -> contactMapper.toEmail(contactCreateDto, client);
            default -> throw new ContactValidationException(Error.CONTACT_TYPE_ERR);
        };
    }

    private void validateContactValue(ContactType contactType, String value) {
        if (doesContactExist(value)) {
            throw new ContactTypeExistsException(Error.CONTACT_EXISTS_ERR);
        } else {
            String regexPattern = switch (contactType) {
                case PHONE -> PHONE_REGEX;
                case EMAIL -> EMAIL_REGEX;
            };
            if (!Pattern.compile(regexPattern).matcher(value).matches()) {
                throw new ContactValidationException(Error.CONTACT_TYPE_ERR);
            }
        }
    }

    public ContactCreateDto toContactDto(Contact contact) {
        return contactMapper.mapToDto(contact);
    }

    private boolean doesContactExist(String value) {
        return contactRepository.existsByValue(value);
    }
}
