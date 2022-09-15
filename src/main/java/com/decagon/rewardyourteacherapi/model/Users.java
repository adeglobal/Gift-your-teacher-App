package com.decagon.rewardyourteacherapi.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profileImage;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "school_id")
    private School school;


    public Users(long id, String firstName, String lastName, String email , String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passWord = password;
    }
}
