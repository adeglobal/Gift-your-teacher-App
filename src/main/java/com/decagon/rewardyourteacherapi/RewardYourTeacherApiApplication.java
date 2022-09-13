package com.decagon.rewardyourteacherapi;

import com.decagon.rewardyourteacherapi.security.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyProperties.class)
public class RewardYourTeacherApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardYourTeacherApiApplication.class, args);
	}

}
