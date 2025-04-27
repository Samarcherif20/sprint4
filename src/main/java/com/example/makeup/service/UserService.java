package com.example.makeup.service;

import com.example.makeup.entities.Role;
import com.example.makeup.entities.User;

public interface UserService {
		void deleteAllusers();
		void deleteAllRoles();
		User saveUser(User user);
		User findUserByUsername (String username);
		Role addRole(Role role);
		User addRoleToUser(String username, String rolename);
}
