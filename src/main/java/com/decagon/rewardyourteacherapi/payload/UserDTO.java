package com.decagon.rewardyourteacherapi.payload;

import com.decagon.rewardyourteacherapi.enums.Role;
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
    private  String phoneNumber;
    private String imageUrl;
    private BigDecimal wallet;

    private Role role;



    public UserDTO(Long id, String firstname, String lastname, String phoneNumber, String imageUrl,BigDecimal wallet) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imageUrl = imageUrl;
        this.wallet = wallet;
        this.phoneNumber = phoneNumber;
    }
    public UserDTO(String firstname, String lastname, String imageUrl) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.imageUrl = imageUrl;
    }
}
