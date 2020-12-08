package com.fptu.paa.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.RoleName;
import com.fptu.paa.controller.request.LoginRequest;
import com.fptu.paa.controller.request.RegisterStaffRequest;
import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.entity.User;

@Service
public interface UserService {
	String loginViaGmail(LoginRequest loginRequest);

	String loginViaUsername(LoginRequest loginRequest);

	User registerStaffAccount(RegisterStaffRequest userDTO);

	UserViewDTO getUserDetail(Long idUser);

	UserViewDTO updateUserProfile(UserViewDTO userViewDTO);

	boolean rechargeBalance(Long userId, BigDecimal balance);

	boolean changeAccountStatus(Long userID, boolean status);

	UserViewDTO getCurrentUser();
	
	boolean ticketPaymnet(String price, Long userID);

	List<UserViewDTO> getUsersByRole(RoleName roleName);
	
	User insertDefaultAdmin();
}
