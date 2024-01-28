package ru.unibell.clientinfoapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.unibell.clientinfoapi.models.dto.ContactDto;
import ru.unibell.clientinfoapi.service.impl.ContactServiceImpl;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ContactServiceImpl contactService;


    // Add a new contact for a client
    @PostMapping("/client/{id}")
    public ResponseEntity<ContactDto> addContact(@PathVariable("id") Long id, @Valid @RequestBody ContactDto contactDto) {
        logger.info("Received a request to add a new contact for client with ID: {}", id);
        ContactDto savedContact = contactService.save(id, contactDto);
        if (savedContact != null) {
            return ResponseEntity.ok(savedContact);
        } else {
            logger.warn("Failed to add contact for client with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}