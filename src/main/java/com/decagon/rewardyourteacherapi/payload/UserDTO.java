package com.decagon.rewardyourteacherapi.payload;

import com.decagon.rewardyourteacherapi.enums.Role;
import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String schoolName;
    private String name;
    private String email;
    private String password;
    private  String phoneNumber;
    private String imageUrl;
    private BigDecimal wallet;

    private Role role;
    private String yearsOfTeaching;
    private String subjectTaught;
    private String schoolType;
    private String status;
    private String about;
    private String position;





    public UserDTO(Long id, String name, String lastname, String imageUrl, BigDecimal wallet) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.wallet = wallet;

    }


    public UserDTO(long id, String name, String profileImage, BigDecimal wallet) {
    }

    public UserDTO(String name, String email, String password) {
        this.name=name;
        this.email=email;
        this.password=password;
    }
}
