package com.ums.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ums.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //let serialize nested objects
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

    public static User convertFrom(UserDTO userDTO,UserGroup userGroup) {
        if (userDTO == null)
            return null;

        final User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDate(userDTO.getDate());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setGroup(userGroup);
        return user;
    }
}
