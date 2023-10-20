package ru.unibell.clientinfoapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.unibell.clientinfoapi.dto.ContactCreateDto;
import ru.unibell.clientinfoapi.entity.Client;
import ru.unibell.clientinfoapi.entity.Contact;
import ru.unibell.clientinfoapi.entity.ContactType;


@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(target = "client")
    default Contact toPhone(ContactCreateDto contactCreateDto, Client client) {
        Contact phoneContact = Contact.createContact(ContactType.PHONE);
        phoneContact.setClient(client);
        phoneContact.setValue(contactCreateDto.getValue());
        return phoneContact;
    }

    @Mapping(target = "client")
    default Contact toEmail(ContactCreateDto contactCreateDto, Client client) {
        Contact emailContact = Contact.createContact(ContactType.EMAIL);
        emailContact.setClient(client);
        emailContact.setValue(contactCreateDto.getValue());
        return emailContact;
    }

    ContactCreateDto mapToDto(Contact contact);

}
