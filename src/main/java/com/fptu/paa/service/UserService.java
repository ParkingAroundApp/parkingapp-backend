package com.fptu.paa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.RoleName;
import com.fptu.paa.dto.LoginRequest;
import com.fptu.paa.dto.UserDTO;
import com.fptu.paa.dto.UserViewDTO;

@Service
public interface UserService {
	String loginViaGmail(LoginRequest loginRequest);

	String loginViaUsername(LoginRequest loginRequest);

	UserDTO registerOwnerAccount(UserDTO userDTO);

	UserDTO getUserDetail(Long idUser);

	UserViewDTO updateUserProfile(UserViewDTO userViewDTO);

	boolean disableAccount(Long userID);

	UserViewDTO getCurrentUser();
	
	boolean ticketPaymnet(String price, Long userID);

	List<UserViewDTO> getUsersByRole(RoleName roleName);
}
