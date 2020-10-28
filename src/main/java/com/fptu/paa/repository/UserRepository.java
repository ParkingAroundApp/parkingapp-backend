package com.fptu.paa.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptu.paa.entity.Role;
import com.fptu.paa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
	
	public User findUserById(Long id);
	
	public List<User> findByRoles(Role roles);
	
}
