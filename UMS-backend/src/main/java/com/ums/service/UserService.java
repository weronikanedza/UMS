package com.ums.service;

import com.ums.entity.User;
import com.ums.entity.UserGroup;
import com.ums.exception.OperationException;

public interface UserService {
    void save(User user) throws OperationException; //save user in DB
    void checkIfUserExists(String userName) throws OperationException;
    UserGroup getUserGroup(Long id);
}
