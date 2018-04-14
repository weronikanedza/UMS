package com.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GroupDTO implements Serializable {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
}
