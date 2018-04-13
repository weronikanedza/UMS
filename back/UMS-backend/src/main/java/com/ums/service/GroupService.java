package com.ums.service;

import com.ums.entity.UserGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    List<UserGroup> getAllGroups();
    UserGroup retrieveById(Long id);
}
