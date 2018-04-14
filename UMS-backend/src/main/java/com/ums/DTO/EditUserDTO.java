package com.ums.dto;

import com.ums.entity.User;
import com.ums.entity.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class EditUserDTO implements Serializable{
    private User user;
    private List<UserGroup> userGroups;
}
