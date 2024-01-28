package ru.unibell.clientinfoapi.mapper;

import org.mapstruct.Mapper;
import ru.unibell.clientinfoapi.models.dto.ClientDto;
import ru.unibell.clientinfoapi.models.dto.ContactDto;
import ru.unibell.clientinfoapi.models.entity.Client;
import ru.unibell.clientinfoapi.models.entity.ContactType;
import ru.unibell.clientinfoapi.models.entity.EmailContact;
import ru.unibell.clientinfoapi.models.entity.PhoneContact;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ContactMapper.class})
public interface ClientMapper {

    Client toClient(ClientDto ClientDto);


    ClientDto toDto(Client client);

    default List<ContactDto> toContactsList(Client client) {
        List<ContactDto> contacts = new ArrayList<>();
        contacts.addAll(toEmailsList(client));
        contacts.addAll(toPhonesList(client));
        return contacts;
    }

    default List<ContactDto> toPhonesList(Client client) {
        List<ContactDto> phoneContacts = new ArrayList<>();
        if (client != null && client.getPhoneContacts() != null) {
            for (PhoneContact phoneContact : client.getPhoneContacts()) {
                ContactDto phoneDto = new ContactDto();
                phoneDto.setValue(phoneContact.getValue());
                phoneDto.setContactType(ContactType.valueOf(phoneContact.getContactType()));
                phoneContacts.add(phoneDto);
            }
        }
        return phoneContacts;
    }

    default List<ContactDto> toEmailsList(Client client) {
        List<ContactDto> emailContacts = new ArrayList<>();
        if (client != null && client.getEmailContacts() != null) {
            for (EmailContact emailContact : client.getEmailContacts()) {
                ContactDto emailDto = new ContactDto();
                emailDto.setValue(emailContact.getValue());
                emailDto.setContactType(ContactType.valueOf(emailContact.getContactType()));
                emailContacts.add(emailDto);
            }
        }
        return emailContacts;
    }


}
