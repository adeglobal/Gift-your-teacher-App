package com.decagon.rewardyourteacherapi.payload;

import com.decagon.rewardyourteacherapi.model.Role;
import lombok.*;

import java.math.BigDecimal;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private BigDecimal wallet;
    private String imageUrl;

    public UserDTO(Long id, String firstname, String lastname, BigDecimal wallet, String imageUrl) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.wallet = wallet;
        this.imageUrl = imageUrl;
    }
    public UserDTO(String firstname, String lastname, String imageUrl) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.imageUrl = imageUrl;
    }
}
