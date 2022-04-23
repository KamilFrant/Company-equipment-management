package pl.kfrant.personelmanagement.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kfrant.personelmanagement.user.dto.UserAssignmentDto;
import pl.kfrant.personelmanagement.user.dto.UserDto;

import java.net.URI;
import java.util.List;

@Api(tags = "Users")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @ApiOperation("Take all users")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(required = false) String lastName) {
        if (lastName != null) {
            return ResponseEntity.ok(userService.findByLastName(lastName));
        } else
            return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    @ApiOperation("Take user by id")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ApiOperation("Create new user")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywanu użytkownik nie może mieć ustawionego ID.");
        }
        UserDto savedUser = userService.saveUser(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update user")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        if (!id.equals(dto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aktualizowany użytkownik ma złe ID");
        }
        UserDto updatedUser = userService.updateUser(dto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}/assignments")
    @ApiOperation("Take all assignments for this user")
    public ResponseEntity<List<UserAssignmentDto>> getUserAssignments(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserAssignments(id));
    }

}
