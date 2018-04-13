package com.ums.service;

import com.ums.entity.UserGroup;

import java.util.List;

public interface GroupService {
    List<UserGroup> getAllGroups();
    UserGroup retrieveById(Long id);
}
