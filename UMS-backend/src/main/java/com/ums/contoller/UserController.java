package com.ums.contoller;

import com.ums.DTO.EditUserDTO;
import com.ums.DTO.UserDTO;
import com.ums.entity.User;
import com.ums.entity.UserGroup;
import com.ums.exception.OperationException;
import com.ums.service.UserService;
import com.ums.validation.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
public class UserController {

    private UserService userService;
    private PasswordValidation passwordValidation;
    private PasswordEncoder passwordEncoder; //less hassle

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder=passwordEncoder;
        this.passwordValidation = new PasswordValidation();
    }

    @PostMapping(path = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody @Valid final UserDTO user) throws OperationException {

        passwordValidation.passwordMatches(user.getPassword(),user.getPasswordConfirm());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserGroup userGroup=userService.getUserGroup(user.getGroupId());
        userService.save(User.convertFrom(user,userGroup));

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(userService.getAllUsers());
    }

    @PostMapping(path="/editUser",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editUser(@RequestBody @Valid UserDTO user) throws OperationException{
        passwordValidation.passwordMatches(user.getPassword(),user.getPasswordConfirm());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserGroup userGroup=userService.getUserGroup(user.getGroupId());
        userService.update(User.convertFrom(user,userGroup));

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping(path="/getUserById",consumes="application/json")
    public ResponseEntity getUser(@RequestBody Long id){
        EditUserDTO editUserDTO=new EditUserDTO(userService.retrieveById(id),userService.getAllGroups());
        return ResponseEntity.ok(editUserDTO);
    }

    @PostMapping(path="/removeUser",consumes="application/json")
    public ResponseEntity removeUser(@RequestBody Long id){
        userService.removeUserById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}

