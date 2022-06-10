package projetPOEIspring.poeidata.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import projetPOEIspring.poeidata.api.dto.ClientDto;
import projetPOEIspring.poeidata.exceptions.NotAllowedToDeleteClientException;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.mappers.ClientMapper;
import projetPOEIspring.poeidata.models.Client;
import projetPOEIspring.poeidata.services.ClientService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/clients")
public class ClientApi {

    Logger log = LoggerFactory.getLogger(ClientApi.class);

    @Autowired
    private final ClientMapper clientMapper;

    @Autowired
    private final ClientService clientService;

    public ClientApi(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all clients ordered by lastname ascending.")
    public ResponseEntity<List<ClientDto>> getAll() {
        log.info("Retrieving customers.");
        return ResponseEntity.ok(
                this.clientService.getAll().stream().map(this.clientMapper::mapToClientDto).toList()
        );
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Trying to retrieve a client from the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the client found the given ID"),
            @ApiResponse(responseCode = "404", description = "No client found the given ID")
    })
    public ResponseEntity<ClientDto> findById(@PathVariable final Integer id) {
        try {
            return ResponseEntity.ok(this.clientMapper.mapToClientDto(this.clientService.getById(id)));
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Create client")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<ClientDto> createClient(@RequestBody final ClientDto clientDto) {
        log.debug("Attempting to create client with lastname {}", clientDto.getNom());

        ClientDto clientDtoResponse =
                this.clientMapper.mapToClientDto(
                        this.clientService.createClient(
                                this.clientMapper.mapToClient(clientDto)
                        )
                );
        return ResponseEntity
                .created(URI.create("/v1/clients/" + clientDtoResponse.getId()))
                .body(clientDtoResponse);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a client for the given ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "403", description = "Cannot delete the client for the given ID"),
            @ApiResponse(responseCode = "404", description = "No client found the given ID")
    })

    public ResponseEntity<Void> deleteClient(@PathVariable final Integer id) {
        log.debug("Attempting to delete client with ID {}", id);
        try {
            this.clientService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        } catch (NotAllowedToDeleteClientException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Update a client")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
    })
    public ResponseEntity<Client> updateClient(@PathVariable final Integer id,
                                               @RequestBody ClientDto clientDto) {
        try {
            log.debug("Updating client {}", clientDto.getId());
            clientDto.setId(id);
            this.clientService.updateClient(clientMapper.mapToClient(clientDto));
            log.debug("Successfully updated client {}", clientDto.getId());
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }
}
