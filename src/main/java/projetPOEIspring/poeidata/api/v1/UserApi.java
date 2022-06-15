package projetPOEIspring.poeidata.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import projetPOEIspring.poeidata.api.dto.UserDto;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.mappers.UserMapper;
import projetPOEIspring.poeidata.services.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/admin")
public class UserApi {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserApi(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all users")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(
                this.userService.getAll().stream().map(this.userMapper::mapToDto).toList()
        );
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Trying to retrieve an user from the given ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the user found the given ID"),
            @ApiResponse(responseCode = "404", description = "No user found the given ID")
    })
    public ResponseEntity<UserDto> findById(@PathVariable final Integer id) {
        try {
            return ResponseEntity.ok(this.userMapper.mapToDto(this.userService.getById(id)));
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "create user")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<UserDto> createUser(@RequestBody final UserDto userDto) {

        UserDto userDtoResponse =
                this.userMapper.mapToDto(
                        this.userService.createUser(
                                this.userMapper.mapToModel(userDto)
                        )
                );
        return ResponseEntity.created(URI.create("/v1/admin/" + userDtoResponse.getId())).body(userDtoResponse);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Update an user")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
    })
    public ResponseEntity<UserDto> updateUser(@PathVariable final Integer id,
                                              @RequestBody UserDto userDto) {

            userDto.setId(id);
            UserDto updateUser = userMapper.mapToDto(userService.updateUser(userMapper.mapToModel(userDto)));
            return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete an user for the given id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "403", description = "Cannot delete the user for the given ID"),
            @ApiResponse(responseCode = "404", description = "No user found the given ID")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable final Integer id) {

            this.userService.deleteUser(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/connexion", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Return a user by its mail and password")
    public ResponseEntity<UserDto> login(@RequestParam String mail, @RequestParam String password) {
        try {
            return ResponseEntity.ok(
                    userMapper.mapToDto(userService.getUserByMailAndPassword(mail, password))
            );
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }
}
