package ru.unibell.clientinfoapi.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.unibell.clientinfoapi.dto.ClientContactsDto;
import ru.unibell.clientinfoapi.dto.ClientCreateDto;
import ru.unibell.clientinfoapi.dto.ClientResponseDto;
import ru.unibell.clientinfoapi.dto.ContactCreateDto;
import ru.unibell.clientinfoapi.service.impl.ClientServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientServiceImpl clientService;


    // Add a new client
    @PostMapping
    public ResponseEntity<ClientCreateDto> addClient(@Valid @RequestBody ClientCreateDto clientCreateDto) {
        logger.info("Received a request to add a new client.");
        ClientCreateDto savedClient = clientService.save(clientCreateDto);
        logger.info("Client added successfully. Client name: {}", savedClient.getFirstName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    // Get a list of all clients with pagination
    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> getAllClients(@PageableDefault(size = 9) Pageable pageable) {
        logger.info("Received a request to fetch a list of clients with pagination. Page: {}, Size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<ClientResponseDto> clients = clientService.getPaginatedClients(pageable);
        logger.info("Fetched {} clients successfully.", clients.getTotalElements());
        return ResponseEntity.ok(clients);
    }

    // Find a client by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findClientById(@PathVariable("id") Long id) {
        logger.info("Received a request to find a client by ID: {}", id);
        ClientResponseDto client = clientService.findById(id);
        if (client != null) {
            logger.info("Client found successfully. Client ID: {}", client.getClientId());
            return ResponseEntity.ok(client);
        } else {
            logger.warn("Client with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Get a client's contacts by ID
    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<ClientContactsDto>> getClientWithContacts(@PathVariable("id") Long id) {
        logger.info("Received a request to fetch contacts for a client with ID: {}", id);
        List<ClientContactsDto> clientContacts = clientService.getContactsByClientId(id);
        if (clientContacts != null) {
            logger.info("Fetched {} contacts for client ID: {}", clientContacts.size(), id);
            return ResponseEntity.ok(clientContacts);
        } else {
            logger.warn("Contacts not found for client with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/contact")
    public ResponseEntity<List<ContactCreateDto>> findClientContacts(@PathVariable("id") Long id,
                                                                     @RequestParam(value = "type", required = false) String type) {
        return ResponseEntity.ok(clientService.getClientContactsByType(id, type));
    }

}
