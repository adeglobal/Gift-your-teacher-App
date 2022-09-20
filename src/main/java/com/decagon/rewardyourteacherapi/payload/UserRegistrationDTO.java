package com.decagon.rewardyourteacherapi.payload;

import com.decagon.rewardyourteacherapi.model.Role;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UserRegistrationDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private String imageUrl;
}
