package com.fptu.paa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fptu.paa.dto.UserDTO;
import com.fptu.paa.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserDetail(Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateUserProfile(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
