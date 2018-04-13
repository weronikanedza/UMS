package com.ums.entity;

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

    @OneToOne
    @JoinColumn(name="usergroup_id")
    private UserGroup group;

    public User() {
    }
}
