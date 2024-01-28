package ru.unibell.clientinfoapi.validator;


import org.springframework.stereotype.Service;
import ru.unibell.clientinfoapi.exception.ContactTypeExistsException;
import ru.unibell.clientinfoapi.exception.ContactValidationException;
import ru.unibell.clientinfoapi.exception.Error;
import ru.unibell.clientinfoapi.models.entity.ContactType;
import ru.unibell.clientinfoapi.repository.ContactRepository;

import java.util.regex.Pattern;

@Service
public class ContactValidator {

    private static final String PHONE_REGEX = "^(?:\\+7|7|8)[\\s-]?(\\d{3})[\\s-]?(\\d{2})[\\s-]?(\\d{2})$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private final ContactRepository contactRepository;

    public ContactValidator(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void validateContact(ContactType contactType, String value) {
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

    private boolean doesContactExist(String value) {
        return contactRepository.existsByValue(value);
    }
}
