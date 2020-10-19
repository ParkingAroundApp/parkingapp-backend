package com.fptu.paa.service;

import org.springframework.stereotype.Service;

import com.fptu.paa.dto.LoginRequest;
import com.fptu.paa.dto.UserDTO;
import com.fptu.paa.dto.UserViewDTO;

@Service
public interface UserService {
	String loginViaGmail(LoginRequest loginRequest);
	
	String loginViaUsername(LoginRequest loginRequest);
	
	UserDTO registerOwnerAccount(UserDTO userDTO);

	UserDTO getUserDetail(Long idUser);

	UserDTO updateUserProfile(UserDTO userDTO);

	boolean disableAccount(Long userID);

	UserViewDTO getCurrentUser();
}
