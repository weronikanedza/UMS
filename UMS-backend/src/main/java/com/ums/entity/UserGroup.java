package com.ums.entity;

import com.ums.dto.GroupDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usergroup")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public UserGroup(String name) {
        this.name = name;
    }

    public static UserGroup convertFrom(GroupDTO groupDTO){
        if (groupDTO == null)
            return null;

        UserGroup userGroup=new UserGroup();
        userGroup.setId(Long.parseLong(groupDTO.getId()));
        userGroup.setName(groupDTO.getName());
        return userGroup;
    }
}
