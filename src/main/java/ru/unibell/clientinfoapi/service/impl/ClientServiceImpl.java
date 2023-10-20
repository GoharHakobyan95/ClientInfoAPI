package ru.unibell.clientinfoapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.unibell.clientinfoapi.dto.ClientContactsDto;
import ru.unibell.clientinfoapi.dto.ClientCreateDto;
import ru.unibell.clientinfoapi.dto.ClientResponseDto;
import ru.unibell.clientinfoapi.dto.ContactCreateDto;
import ru.unibell.clientinfoapi.entity.Client;
import ru.unibell.clientinfoapi.entity.ContactType;
import ru.unibell.clientinfoapi.exception.ClientNotFoundException;
import ru.unibell.clientinfoapi.exception.Error;
import ru.unibell.clientinfoapi.mapper.ClientMapper;
import ru.unibell.clientinfoapi.repository.ClientRepository;
import ru.unibell.clientinfoapi.service.ClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Override
    public ClientCreateDto save(ClientCreateDto clientCreateDto) {
        Client savedClient = clientRepository.save(clientMapper.mapToClient(clientCreateDto));
        return clientMapper.mapToDto(savedClient);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(Error.CLIENT_NOT_FOUND));
    }

    @Override
    public Page<ClientResponseDto> getPaginatedClients(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::mapToResponseDto);
    }

    public List<ClientContactsDto> getContactsByClientId(Long id) {
        Client client = getClientById(id);
        return clientMapper.mapToClientWithContacts(client);
    }


    public List<ContactCreateDto> getClientContactsByType(Long id, String type) {
        Client client = getClientById(id);
        if (type != null) {
            ContactType contactType = ContactType.valueOf(type);
            if (contactType == ContactType.PHONE) {
                return clientMapper.mapToPhonesList(client);
            } else if (contactType == ContactType.EMAIL) {
                return clientMapper.mapToEmailsList(client);
            }
        }
        return clientMapper.mapToContactsList(client);
    }

    public ClientResponseDto findById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::mapToResponseDto)
                .orElseThrow(() -> new ClientNotFoundException(Error.CLIENT_NOT_FOUND));

    }
}
