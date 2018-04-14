package com.ums.contoller;

import com.ums.dto.GroupDTO;
import com.ums.entity.UserGroup;
import com.ums.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping(path="/removeGroup",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeUser(@RequestBody Long id){
        groupService.removeGroupById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping(path="/editGroup",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editGroup(@RequestBody @Valid GroupDTO groupDTO){
       groupService.editGroupName(UserGroup.convertFrom(groupDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
