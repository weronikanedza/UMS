package com.ums.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserDTO implements Serializable{

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;
    @NotBlank
    private String date;
    @NotNull
    private Long groupId;

    public UserDTO(){}
}
