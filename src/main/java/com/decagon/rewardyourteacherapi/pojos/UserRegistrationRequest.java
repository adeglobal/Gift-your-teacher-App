package com.decagon.rewardyourteacherapi.pojos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class UserRegistrationRequest {
    //This class is handling the users requests
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
}
