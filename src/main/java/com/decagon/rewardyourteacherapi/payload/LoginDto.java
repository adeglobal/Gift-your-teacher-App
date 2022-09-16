package com.decagon.rewardyourteacherapi.payload;

import lombok.Data;


@Data
public class LoginDto {
    private String email;
    private  String password;
}
