package com.fptu.paa.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.RoleName;
import com.fptu.paa.dto.LoginRequest;
import com.fptu.paa.dto.RegisterStaffRequest;
import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.entity.User;

@Service
public interface UserService {
	String loginViaGmail(LoginRequest loginRequest);

	String loginViaUsername(LoginRequest loginRequest);

	User registerStaffAccount(RegisterStaffRequest userDTO);

	User getUserDetail(Long idUser);

	UserViewDTO updateUserProfile(UserViewDTO userViewDTO);

	boolean rechargeBalance(Long userId, BigDecimal balance);

	boolean disableAccount(Long userID);

	UserViewDTO getCurrentUser();
	
	boolean ticketPaymnet(String price, Long userID);

	List<UserViewDTO> getUsersByRole(RoleName roleName);
}
