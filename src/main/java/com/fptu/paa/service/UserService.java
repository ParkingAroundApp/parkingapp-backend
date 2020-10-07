package com.fptu.paa.service;

import org.springframework.stereotype.Service;

import com.fptu.paa.dto.UserDTO;

@Service
public interface UserService {
	UserDTO createUser(UserDTO userDTO);

	UserDTO getUserDetail(Long idUser);

	UserDTO updateUserProfile(UserDTO userDTO);
}
