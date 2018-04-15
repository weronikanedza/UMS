package com.ums.contoller;

import com.ums.dto.UserDTO;
import com.ums.exception.OperationException;
import com.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody @Valid UserDTO user) throws OperationException {
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping(path="/editUser",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editUser(@RequestBody @Valid UserDTO userDTO) throws OperationException{
        userService.updateUser(userDTO);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping(path="/getUserById",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@RequestBody Long id){
        return ResponseEntity.ok(userService.getUserWithGroup(id));
    }

    @PostMapping(path="/removeUser",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeUser(@RequestBody Long id){
        userService.removeUserById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}

