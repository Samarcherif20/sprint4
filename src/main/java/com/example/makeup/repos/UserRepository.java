package com.example.makeup.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.makeup.entities.User;
public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername (String username);
}


