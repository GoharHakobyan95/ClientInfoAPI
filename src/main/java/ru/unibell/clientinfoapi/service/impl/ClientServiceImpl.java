package ru.unibell.clientinfoapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.unibell.clientinfoapi.exception.ClientNotFoundException;
import ru.unibell.clientinfoapi.exception.Error;
import ru.unibell.clientinfoapi.mapper.ClientMapper;
import ru.unibell.clientinfoapi.mapper.ContactMapper;
import ru.unibell.clientinfoapi.models.dto.ClientDto;
import ru.unibell.clientinfoapi.models.dto.ContactDto;
import ru.unibell.clientinfoapi.models.entity.*;
import ru.unibell.clientinfoapi.repository.ClientRepository;
import ru.unibell.clientinfoapi.repository.ContactRepository;
import ru.unibell.clientinfoapi.service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ContactRepository contactRepository;
    private final ClientMapper clientMapper;
    private final ContactMapper contactMapper;

    @Override
    public ClientDto save(ClientDto clientDto) {
        Client savedClient = clientRepository.save(clientMapper.toClient(clientDto));
        return clientMapper.toDto(savedClient);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(Error.CLIENT_NOT_FOUND));
    }

    public List<Contact> getClientContactsById(Long clientId) {
        List<PhoneContact> phoneContacts = contactRepository.findPhoneContactsByClientClientId(clientId);
        List<EmailContact> emailContacts = contactRepository.findEmailContactsByClientClientId(clientId);
        List<Contact> allContacts = new ArrayList<>();
        allContacts.addAll(emailContacts);
        allContacts.addAll(phoneContacts);
        return allContacts;
    }

    @Override
    public Page<ClientDto> getPaginatedClients(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toDto);
    }

    public List<ContactDto> getContactsByClientId(Long id) {
        List<Contact> clientContactsById = getClientContactsById(id);
        return contactMapper.toDtoList(clientContactsById);
    }


    public List<ContactDto> getClientContactsByType(Long id, String type) {
        Client client = getClientById(id);
        if (type != null) {
            ContactType contactType = ContactType.valueOf(type);
            if (contactType == ContactType.PHONE) {
                return clientMapper.toPhonesList(client);
            } else if (contactType == ContactType.EMAIL) {
                return clientMapper.toEmailsList(client);
            }
        }
        return clientMapper.toContactsList(client);
    }

    public ClientDto findById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new ClientNotFoundException(Error.CLIENT_NOT_FOUND));

    }

    @Override
    public void deleteClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new ClientNotFoundException(Error.CLIENT_NOT_FOUND);
        }
    }
}
