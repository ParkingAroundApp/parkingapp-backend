package com.fptu.paa.service;

import java.math.BigDecimal;
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

	boolean rechargeBalance(Long userId, BigDecimal balance);

	boolean disableAccount(Long userID);

	UserViewDTO getCurrentUser();

	List<UserViewDTO> getUsersByRole(RoleName roleName);
}
