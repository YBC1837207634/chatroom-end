package com.example.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String headImage;
    private String name;
    private boolean isDelete;
    private boolean status;
    private boolean admin;
}
