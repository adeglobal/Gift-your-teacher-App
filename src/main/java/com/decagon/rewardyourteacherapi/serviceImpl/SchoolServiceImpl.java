package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.repository.SchoolRepository;
import com.decagon.rewardyourteacherapi.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Page<School> retrieveSchools(int page, int size) {
       return repo.findAll(PageRequest.of(page,size));
    }
}
