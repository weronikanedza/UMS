package com.ums.service;

import com.ums.entity.UserGroup;

import java.util.List;

public interface GroupService {
    List<UserGroup> getAllGroups();
    UserGroup retrieveById(Long id);
    void removeGroupById(Long id);
    void editGroupName(UserGroup userGroup);
    UserGroup addGroup(String name);
}
