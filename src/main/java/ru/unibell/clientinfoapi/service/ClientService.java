package ru.unibell.clientinfoapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.unibell.clientinfoapi.dto.ClientCreateDto;
import ru.unibell.clientinfoapi.dto.ClientResponseDto;
import ru.unibell.clientinfoapi.entity.Client;

public interface ClientService {

    ClientCreateDto save(ClientCreateDto clientDto);

    Client getClientById(Long id);

    Page<ClientResponseDto> getPaginatedClients(Pageable pageable);


}