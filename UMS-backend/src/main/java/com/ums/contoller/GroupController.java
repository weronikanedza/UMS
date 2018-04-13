package com.ums.contoller;

import com.ums.entity.UserGroup;
import com.ums.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path="/getGroups",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserGroup>> getGroups(){
        return ResponseEntity.ok(groupService.getAllGroups());
    }
}
