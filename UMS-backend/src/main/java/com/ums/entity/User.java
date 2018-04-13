package com.ums.entity;

import com.ums.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String date;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="usergroup_id")
    private UserGroup group;

    public User() {
    }

    public static User convertFrom(UserDTO userDTO,UserGroup userGroup) {
        if (userDTO == null)
            return null;

        final User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDate(userDTO.getDate());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setGroup(userGroup);
        return user;
    }

}
