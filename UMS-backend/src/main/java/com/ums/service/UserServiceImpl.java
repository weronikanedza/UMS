package com.ums.service;

import com.ums.entity.User;
import com.ums.entity.UserGroup;
import com.ums.exception.OperationException;
import com.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private GroupService groupService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GroupService groupService) {
        this.userRepository = userRepository;
        this.groupService=groupService;
    }


    @Override
    public void save(User user) throws OperationException{
        System.out.println(user);
        checkIfUserExists(user.getUserName());
        userRepository.save(user);
    }

    @Override
    public void checkIfUserExists(String userName) throws OperationException{
        if(userRepository.findByUserName(userName)!=null)
            throw new OperationException(HttpStatus.NOT_ACCEPTABLE,"User already exists");
    }

    public UserGroup getUserGroup(Long id){
        return groupService.retrieveById(id);
    }
    }
