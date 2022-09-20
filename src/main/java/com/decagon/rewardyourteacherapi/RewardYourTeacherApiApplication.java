package com.decagon.rewardyourteacherapi;


import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.service.SchoolService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class RewardYourTeacherApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardYourTeacherApiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(SchoolService schoolService) {
		if (schoolService.getSchools() < 1) {
			return args -> {
				// read json and write to db
				ObjectMapper mapper = new ObjectMapper();
				TypeReference<List<School>> typeReference = new TypeReference<>() {
				};
				InputStream inputStream = TypeReference.class.getResourceAsStream("/Schools.json");
				try {
					List<School> schools = mapper.readValue(inputStream, typeReference);
					schoolService.saveSchools(schools);
				} catch (IOException e) {
					throw new RuntimeException("Cannot save school");
				}
			};
		}
		return null;
	}

}
