package com.ums.service;

import com.ums.dto.EditUserDTO;
import com.ums.entity.User;
import com.ums.entity.UserGroup;
import com.ums.exception.OperationException;

import java.util.List;

public interface UserService {
    void save(User user) throws OperationException; //save user in DB
    void checkIfUserExists(String userName) throws OperationException;
    UserGroup getUserGroup(Long id);
    List<User> getAllUsers();
    User retrieveById(Long id);
    void update(User user);
    void removeUserById(Long id);
    EditUserDTO getUserWithGroup(Long id);
}
