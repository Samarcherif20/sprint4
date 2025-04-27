package com.example.makeup.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.makeup.entities.Role;
public interface RoleRepository extends JpaRepository<Role, Long> {
Role findByRole(String role);
}
