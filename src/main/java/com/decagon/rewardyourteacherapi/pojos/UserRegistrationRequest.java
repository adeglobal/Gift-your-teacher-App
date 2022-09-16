package com.decagon.rewardyourteacherapi.pojos;

import com.decagon.rewardyourteacherapi.model.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class UserRegistrationRequest {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    private  Role role;
}
