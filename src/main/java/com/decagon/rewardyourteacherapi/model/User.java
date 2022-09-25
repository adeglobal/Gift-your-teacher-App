package com.decagon.rewardyourteacherapi.model;


import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
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

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public User(long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(long id) {
        this.id = id;
    }
    public User(long id, String firstName, String lastName, String profileImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
    }
}
