package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.payload.SchoolResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SchoolService {
    int getSchools();
    List<School> saveSchools(List<School> school);

    List<SchoolResponse> retrieveSchools(int page, int size);

}
