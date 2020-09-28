package com.fptu.paa.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fptu.paa.dto.UserDTO;

@Service
public interface UserService {
	UserDTO createUser(UserDTO userDTO);

	UserDTO getUserDetail(UUID idUser);

	UserDTO updateUserProfile(UserDTO userDTO);
}
