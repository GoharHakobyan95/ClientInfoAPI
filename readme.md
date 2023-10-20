# Client InfoAPI

## *Description*
This is a simple api that provides information about clients and their contact information.

## Introduction

This project is an API developed using the Spring Framework to work with a database. The API provides the following functions:

1. **Client Creation:** Allows the addition of new clients.
2. **Client Contact Creation:** Supports the creation of client contacts (phone or email).
3. **Client Listing:** Provides a list of clients.
4. **Client Information Retrieval:** Allows retrieval of client information by ID.
5. **Client Contact Listing:** Enables users to retrieve a list of client contacts for a specific client.
6. **Client Contact Filtering:** Allows filtering of client contacts by type for a specific client.

## *Libraries*

Lombok, MapStruct,  MySql Connector J,  Spring Data Jpa.

## *Few Code Examples*
### Endpoint to create a client

``` java
/***/
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
``` 

## *Contributor*

Gohar Hakobyan

### *Email:* hak.goh95@gmail.com 