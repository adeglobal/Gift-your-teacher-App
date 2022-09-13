package com.decagon.rewardyourteacherapi.security;

import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationServiceRepository serviceDao;

    public ApplicationUserService(ApplicationServiceRepository serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = serviceDao.selectUserByEmailAndPassword(email).orElseThrow(() -> new UsernameNotFoundException("user with email "+email+" not found"));
        return new ApplicationUser(user);
    }
}
