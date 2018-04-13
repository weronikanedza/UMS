package com.ums.service;

import com.ums.entity.UserGroup;
import com.ums.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    private GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<UserGroup> getAllGroups() {
       return groupRepository.findAll();
    }

    @Override
    public UserGroup retrieveById(Long id) {
        return groupRepository.getOne(id);
    }

}
