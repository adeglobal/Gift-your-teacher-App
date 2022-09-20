package com.decagon.rewardyourteacherapi.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SchoolResponse {
    private String schoolName;
    private String schoolAddress;
    private String schoolCity;
    private String schoolState;
    private String schoolType;
}
