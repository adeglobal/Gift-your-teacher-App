package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.payload.SchoolResponse;
import com.decagon.rewardyourteacherapi.repository.SchoolRepository;
import com.decagon.rewardyourteacherapi.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository repo;
    @Override
    public int getSchools() {
        return repo.findAll().size();
    }

    @Override
    public List<School> saveSchools(List<School> school) {
        return repo.saveAll(school);
    }

    @Override
    public Page<SchoolResponse> retrieveSchools(int page, int size) {
        Page<School> schools = repo.findAll(PageRequest.of(page,size));
       List<SchoolResponse> schoolResponses = new ArrayList<>();
        schools.forEach(school2->{
            SchoolResponse schoolResponse = SchoolResponse
                    .builder().schoolAddress(school2.getSchoolAddress())
                    .schoolCity(school2.getSchoolCity())
                    .schoolName(school2.getSchoolName())
                    .schoolState(school2.getSchoolState())
                    .schoolType(school2.getSchoolType())
                    .build();
            schoolResponses.add(schoolResponse);

        });
        Page<SchoolResponse> page1 = new PageImpl<>(schoolResponses, PageRequest.of(page, size), schoolResponses.size());

        return page1;
    }
}
