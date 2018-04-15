package com.ums.service;

import com.ums.entity.UserGroup;
import com.ums.exception.OperationException;

import java.util.List;

public interface GroupService {
    List<UserGroup> getAllGroups();
    UserGroup retrieveById(Long id);
    UserGroup addGroup(String name) throws OperationException;
    void removeGroupById(Long id);
    void editGroupName(UserGroup userGroup) throws OperationException;
}
