package com.ums.service;

import com.ums.dto.EditUserDTO;
import com.ums.dto.UserDTO;
import com.ums.entity.User;
import com.ums.entity.UserGroup;
import com.ums.exception.OperationException;
import com.ums.repository.UserRepository;
import com.ums.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private GroupService groupService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GroupService groupService,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.groupService=groupService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDTO) throws OperationException{
        UserValidation.validate(userDTO);
        passwordSetUp(userDTO);
        User user=createUser(userDTO);
        checkIfUserExists(user.getUserName());
        userRepository.save(user);
    }

    @Override
    public void checkIfUserExists(String userName) throws OperationException{
        if(userRepository.findByUserName(userName)!=null)
            throw new OperationException(HttpStatus.NOT_ACCEPTABLE,"User already exists");
    }

    @Override
    public void updateUser(UserDTO userDTO) throws OperationException{
        UserValidation.validate(userDTO);
        passwordSetUp(userDTO);
        UserGroup userGroup=groupService.retrieveById(userDTO.getGroupId());
        User user=User.convertFrom(userDTO,userGroup);
        userRepository.updateUser(user.getUserName(),user.getFirstName(),user.getDate(),
                user.getLastName(),user.getPassword(),user.getGroup(),user.getId());
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.removeUser(id);
    }

    @Override
    public EditUserDTO getUserWithGroup(Long id) {
        return new EditUserDTO(retrieveById(id),groupService.getAllGroups());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User retrieveById(Long id) {
        return userRepository.getOne(id);
    }

    private void passwordSetUp(UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }

    private User createUser(UserDTO userDTO){
        UserGroup userGroup=groupService.retrieveById(userDTO.getGroupId());
        return User.convertFrom(userDTO,userGroup);
    }
}
