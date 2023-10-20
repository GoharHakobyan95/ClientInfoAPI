package ru.unibell.clientinfoapi.mapper;

import org.mapstruct.Mapper;
import ru.unibell.clientinfoapi.dto.ClientContactsDto;
import ru.unibell.clientinfoapi.dto.ClientCreateDto;
import ru.unibell.clientinfoapi.dto.ClientResponseDto;
import ru.unibell.clientinfoapi.dto.ContactCreateDto;
import ru.unibell.clientinfoapi.entity.Client;
import ru.unibell.clientinfoapi.entity.ContactType;
import ru.unibell.clientinfoapi.entity.EmailContact;
import ru.unibell.clientinfoapi.entity.PhoneContact;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ContactMapper.class})
public interface ClientMapper {

    ClientResponseDto mapToResponseDto(Client client);

    ClientCreateDto mapToDto(Client client);

    Client mapToClient(ClientCreateDto clientCreateDto);

    default List<ClientContactsDto> mapToClientWithContacts(Client client) {
        List<ClientContactsDto> clientsDtoList = new ArrayList<>();
        if (client != null) {
            ClientContactsDto clientDto = new ClientContactsDto();
            clientDto.setClientId(client.getClientId());
            clientDto.setFirstName(client.getFirstName());
            clientDto.setLastName(client.getLastName());
            clientDto.setEmails(client.getEmailContacts());
            clientDto.setPhones(client.getPhoneContacts());
            clientsDtoList.add(clientDto);
        }
        return clientsDtoList;
    }


    default List<ContactCreateDto> mapToContactsList(Client client) {
        List<ContactCreateDto> contacts = new ArrayList<>();
        contacts.addAll(mapToPhonesList(client));
        contacts.addAll(mapToEmailsList(client));
        return contacts;
    }

    default List<ContactCreateDto> mapToPhonesList(Client client) {
        List<ContactCreateDto> phoneContacts = new ArrayList<>();
        if (client != null && client.getPhoneContacts() != null) {
            for (PhoneContact phoneContact : client.getPhoneContacts()) {
                ContactCreateDto phoneDto = new ContactCreateDto();
                phoneDto.setValue(phoneContact.getValue());
                phoneDto.setContactType(ContactType.valueOf(phoneContact.getContactType()));
                phoneContacts.add(phoneDto);
            }
        }
        return phoneContacts;
    }

    default List<ContactCreateDto> mapToEmailsList(Client client) {
        List<ContactCreateDto> emailContacts = new ArrayList<>();
        if (client != null && client.getEmailContacts() != null) {
            for (EmailContact emailContact : client.getEmailContacts()) {
                ContactCreateDto emailDto = new ContactCreateDto();
                emailDto.setValue(emailContact.getValue());
                emailDto.setContactType(ContactType.valueOf(emailContact.getContactType()));
                emailContacts.add(emailDto);
            }
        }
        return emailContacts;
    }


}
