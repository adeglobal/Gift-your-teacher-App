package com.decagon.rewardyourteacherapi.payload;

import com.decagon.rewardyourteacherapi.enums.Role;
import lombok.*;

import java.math.BigDecimal;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String name;
    private String email;
    private String password;
    private  String phoneNumber;
    private String imageUrl;
    private BigDecimal wallet;

    private Role role;



    public UserDTO(Long id, String name, String lastname, String phoneNumber, String imageUrl, BigDecimal wallet) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.wallet = wallet;
        this.phoneNumber = phoneNumber;
    }
    public UserDTO(String name, String lastname, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public UserDTO(long id, String name, String phoneNumber, String profileImage, BigDecimal wallet) {
    }
}
