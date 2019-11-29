package com.example.demo_data.repository;

import com.example.demo_data.model.UserModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	UserModel findByEmail(String email);
}
