package ru.unibell.clientinfoapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.unibell.clientinfoapi.models.dto.ContactDto;
import ru.unibell.clientinfoapi.models.entity.Client;
import ru.unibell.clientinfoapi.models.entity.Contact;
import ru.unibell.clientinfoapi.models.entity.ContactType;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDto mapToDto(Contact contact);


    List<ContactDto> toDtoList(List<Contact> contacts);

    @Mapping(target = "client")
    default Contact toPhone(ContactDto contactCreateDto, Client client) {
        Contact phoneContact = Contact.createContact(ContactType.PHONE);
        phoneContact.setClient(client);
        phoneContact.setValue(contactCreateDto.getValue());
        return phoneContact;
    }

    @Mapping(target = "client")
    default Contact toEmail(ContactDto contactCreateDto, Client client) {
        Contact emailContact = Contact.createContact(ContactType.EMAIL);
        emailContact.setClient(client);
        emailContact.setValue(contactCreateDto.getValue());
        return emailContact;
    }

}
