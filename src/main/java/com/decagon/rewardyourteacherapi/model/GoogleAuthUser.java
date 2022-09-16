package com.decagon.rewardyourteacherapi.model;

import lombok.Data;

@Data

public class GoogleAuthUser {
    // Fields that google is expected to return
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
//    private  Role userRole;
}
