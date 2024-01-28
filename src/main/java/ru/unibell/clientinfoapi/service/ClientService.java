package ru.unibell.clientinfoapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.unibell.clientinfoapi.models.dto.ClientDto;
import ru.unibell.clientinfoapi.models.entity.Client;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    Client getClientById(Long id);

    Page<ClientDto> getPaginatedClients(Pageable pageable);

    void deleteClient(Long id);


}