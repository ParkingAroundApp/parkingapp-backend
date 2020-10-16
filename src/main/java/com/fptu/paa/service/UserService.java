package com.fptu.paa.service;

import org.springframework.stereotype.Service;

import com.fptu.paa.dto.UserDTO;

@Service
public interface UserService {
	UserDTO registerNormalAccount(UserDTO userDTO);
	
	UserDTO registerGmailAccount(UserDTO userDTO); 
	
	UserDTO getUserDetail(Long idUser);

	UserDTO updateUserProfile(UserDTO userDTO);
	
	boolean disableAccount(Long userID);
}
