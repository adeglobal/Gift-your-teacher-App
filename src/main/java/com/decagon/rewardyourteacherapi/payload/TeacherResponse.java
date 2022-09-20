package com.decagon.rewardyourteacherapi.payload;

import com.decagon.rewardyourteacherapi.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TeacherResponse {
    private String firstname;
    private String lastname;
    private String email;
    private String imageUrl;
}
