package ru.unibell.clientinfoapi.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.unibell.clientinfoapi.models.dto.ClientDto;
import ru.unibell.clientinfoapi.models.dto.ContactDto;
import ru.unibell.clientinfoapi.models.response.ClientResponse;
import ru.unibell.clientinfoapi.models.response.ContactResponse;
import ru.unibell.clientinfoapi.service.impl.ClientServiceImpl;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientServiceImpl clientService;


    // Add a new client
    @PostMapping
    public ResponseEntity<ClientDto> addClient(@Valid @RequestBody ClientDto clientDto) {
        logger.info("Received a request to add a new client.");
        ClientDto savedClient = clientService.save(clientDto);
        logger.info("Client added successfully. Client name: {}", savedClient.getFirstName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    // Get a list of all clients with pagination
    @GetMapping
    public ResponseEntity<Page<ClientResponse>> getAllClients(@PageableDefault(size = 9) Pageable pageable) {
        logger.info("Received a request to fetch a list of clients with pagination. Page: {}, Size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<ClientDto> clients = clientService.getPaginatedClients(pageable);
        logger.info("Fetched {} clients successfully.", clients.getTotalElements());
        Page<ClientResponse> clientResponses = new PageImpl<>(Collections.singletonList(new ClientResponse(clients)));

        return ResponseEntity.ok(clientResponses);
    }

    // Find a client by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable("id") Long id) {
        logger.info("Received a request to find a client by ID: {}", id);
        ClientDto clientDto = clientService.findById(id);
        if (clientDto != null) {
            logger.info("Client found successfully. Client ID: {}", clientDto.getClientId());
            return ResponseEntity.ok(clientDto);
        } else {
            logger.warn("Client with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Get a client's contacts by ID
    @GetMapping("/{id}/contacts")
    public ResponseEntity<ContactResponse> getClientWithContacts(@PathVariable("id") Long id) {
        logger.info("Received a request to fetch contacts for a client with ID: {}", id);
        List<ContactDto> contactsByClientId = clientService.getContactsByClientId(id);
        if (contactsByClientId != null) {
            logger.info("Fetched {} contacts for client ID: {}", contactsByClientId, id);
            return ResponseEntity.ok(new ContactResponse(clientService.getContactsByClientId(id)));
        } else {
            logger.warn("Contacts not found for client with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/contact")
    public ResponseEntity<ContactResponse> findClientContacts(@PathVariable("id") Long id,
                                                              @RequestParam(value = "type", required = false) String type) {
        return ResponseEntity.ok(new ContactResponse(clientService.getClientContactsByType(id, type)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") Long id,
                                                  @RequestBody ClientDto clientDto) {
        ClientDto existingClient = clientService.findById(id);
        if (existingClient == null) {
            return ResponseEntity.notFound().build();
        }
        existingClient.setFirstName(clientDto.getFirstName());
        existingClient.setLastName(clientDto.getLastName());
        return ResponseEntity.ok(clientService.save(existingClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientResponse> deleteClient(@PathVariable("id") Long id,
                                                       Pageable pageable) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(new ClientResponse(clientService.getPaginatedClients(pageable)));
    }

}
