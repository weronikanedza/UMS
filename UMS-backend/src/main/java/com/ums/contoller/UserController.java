package com.ums.contoller;

import com.ums.DTO.UserDTO;
import com.ums.entity.User;
import com.ums.entity.UserGroup;
import com.ums.exception.OperationException;
import com.ums.service.UserService;
import com.ums.validation.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        System.out.println(user);
        passwordValidation.passwordMatches(user.getPassword(),user.getPasswordConfirm());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserGroup userGroup=userService.getUserGroup(user.getGroupId());
        userService.save(User.convertFrom(user,userGroup));

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}

