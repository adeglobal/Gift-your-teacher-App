package com.decagon.rewardyourteacherapi.model;


import com.decagon.rewardyourteacherapi.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private String name;
    private String email;
    private String password;
    private String profileImage;
    private BigDecimal wallet = new BigDecimal(0.0);
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "school_id")
    private School school;

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public User(long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(long id) {
        this.id = id;
    }
    public User(long id, String name, String profileImage) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
    }

    public User(String email) {
        this.email = email;
    }
}
