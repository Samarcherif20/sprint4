package com.example.makeup;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.makeup.entities.BrandLine;
import com.example.makeup.entities.Makeup;
import com.example.makeup.entities.Role;
import com.example.makeup.entities.User;
import com.example.makeup.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class MakeupApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(MakeupApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper()
	{
	return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		
		repositoryRestConfiguration.exposeIdsFor(Makeup.class,BrandLine.class);
	}
}
