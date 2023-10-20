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
import ru.unibell.clientinfoapi.dto.ContactCreateDto;
import ru.unibell.clientinfoapi.service.impl.ContactServiceImpl;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ContactServiceImpl contactService;


    // Add a new contact for a client
    @PostMapping("/client/{id}")
    public ResponseEntity<ContactCreateDto> addContact(@PathVariable("id") Long id, @Valid @RequestBody ContactCreateDto ContactCreateDto) {
        logger.info("Received a request to add a new contact for client with ID: {}", id);
        ContactCreateDto savedContact = contactService.save(id, ContactCreateDto);
        if (savedContact != null) {
            return ResponseEntity.ok(savedContact);
        } else {
            logger.warn("Failed to add contact for client with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}