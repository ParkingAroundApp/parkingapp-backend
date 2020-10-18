package com.fptu.paa.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fptu.paa.dto.UserDTO;
import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.entity.User;
import com.fptu.paa.repository.UserRepository;
import com.fptu.paa.security.jwt.JwtTokenProvider;
import com.fptu.paa.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	JwtTokenProvider jwtProvider;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private UserRepository userRepository;

	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public UserDTO registerNormalAccount(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO registerGmailAccount(UserDTO userDTO) {
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

	@Override
	public boolean disableAccount(Long userID) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getToken() {
		String[] header = request.getHeader("Authorization").split(" ");
		String token = header[header.length - 1];
		String email = jwtProvider.getEmailFromJwtToken(token);
		return email;
	}

	@Override
	public UserViewDTO getCurrentUser() {
		String[] header = request.getHeader("Authorization").split(" ");
		String token = header[header.length - 1];
		String email = jwtProvider.getEmailFromJwtToken(token);
		User user = userRepository.findByEmail(email);
		if (user !=null) {
			UserViewDTO userView = modelMapper.map(user, UserViewDTO.class);
			return userView;
		}
		return null;
	}

}
