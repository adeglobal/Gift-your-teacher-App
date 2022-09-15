package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class UserOauthService {
    @Autowired
    private UserRepository repo;

    public void updateAuthenticationType(String username, String oauth2ClientName) {
        Resource.AuthenticationType authType = Resource.AuthenticationType.valueOf(oauth2ClientName.toUpperCase());
        repo.updateAuthenticationType(username, authType);
    }
}
