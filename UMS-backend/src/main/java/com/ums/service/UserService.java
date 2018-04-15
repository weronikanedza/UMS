package com.ums.service;

import com.ums.dto.EditUserDTO;
import com.ums.dto.UserDTO;
import com.ums.entity.User;
import com.ums.exception.OperationException;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO) throws OperationException;
    void checkIfUserExists(String userName) throws OperationException;
    void updateUser(UserDTO userDTO) throws OperationException;
    void removeUserById(Long id);
    EditUserDTO getUserWithGroup(Long id);
    List<User> getAllUsers();
    User retrieveById(Long id);
}
