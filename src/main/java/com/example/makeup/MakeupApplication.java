package com.example.makeup;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	}/*

	@PostConstruct
void init_users() {
//ajouter les rôles
userService.addRole(new Role(null,"ADMIN"));
userService.addRole(new Role(null,"AGENT"));
userService.addRole(new Role(null,"USER"));
//ajouter les users
userService.saveUser(new User(null,"admin","123",true,null));
userService.saveUser(new User(null,"samar","123",true,null));
userService.saveUser(new User(null,"user1","123",true,null));
//ajouter les rôles aux users
userService.addRoleToUser("admin", "ADMIN");
userService.addRoleToUser("samar", "USER");
userService.addRoleToUser("samar", "AGENT");
userService.addRoleToUser("user1", "USER");
}*/
	@Bean
	public ModelMapper modelMapper()
	{
	return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		// Uncomment this to test encoded password output
		// System.out.println(passwordEncoder.encode("123"));

		// Optional: expose entity IDs in REST API
		repositoryRestConfiguration.exposeIdsFor(Makeup.class);
	}
}
