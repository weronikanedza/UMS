package com.ums.service;

import com.ums.entity.UserGroup;
import com.ums.exception.OperationException;
import com.ums.repository.GroupRepository;
import com.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserGroup> getAllGroups() {
       return groupRepository.findAll();
    }

    @Override
    public UserGroup retrieveById(Long id) {
        return groupRepository.getOne(id);
    }

    @Override
    public void removeGroupById(Long id) {
        userRepository.removeUsersByGroup(groupRepository.getOne(id)); //remove users from group
        groupRepository.removeById(id); //remove group
    }

    @Override
    public void editGroupName(UserGroup userGroup) throws OperationException {
        UserGroup userGroupTo=addGroup(userGroup.getName());  //add new Group
        userRepository.updateUsersGroups(userGroupTo,groupRepository.getOne(userGroup.getId()));// change groups of users
        groupRepository.removeById(userGroup.getId());//delete previous group
    }

    @Override
    public UserGroup addGroup(String name) throws OperationException {
        checkIfGroupExist(name);
        return groupRepository.save(new UserGroup(name)); //create new group
    }

    private void checkIfGroupExist(String name) throws OperationException{
        if(groupRepository.findByName(name)!=null)
            throw new OperationException(HttpStatus.NOT_ACCEPTABLE,"Group already exists");
    }
}
