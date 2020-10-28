package com.fptu.paa.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fptu.paa.constant.RoleName;
import com.fptu.paa.dto.LoginRequest;
import com.fptu.paa.dto.UserDTO;
import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.entity.Role;
import com.fptu.paa.entity.User;
import com.fptu.paa.entity.Wallet;
import com.fptu.paa.repository.RoleRepository;
import com.fptu.paa.repository.UserRepository;
import com.fptu.paa.security.MyUserDetail;
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
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public UserDTO registerOwnerAccount(UserDTO userDTO) {
		String username = userDTO.getUsername();
		String rawPassword = userDTO.getPassword();
		User temp = new User();
		temp.setEmail(username);
		temp.setPassword(passwordEncoder.encode(rawPassword));
		// Set role
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByName(RoleName.ROLE_OWNER));
		temp.setRoles(roles);
		// Save new owner
		User newOwner = userRepository.save(temp);
		if (newOwner == null) {
			return null;
		}
		return userDTO;
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

	public String decodeEmailFromToken() {
		String[] header = request.getHeader("Authorization").split(" ");
		String token = header[header.length - 1];
		String email = jwtProvider.getEmailFromJwtToken(token);
		return email;
	}

	@Override
	public UserViewDTO getCurrentUser() {
//		String[] header = request.getHeader("Authorization").split(" ");
//		String token = header[header.length - 1];
//		String email = jwtProvider.getEmailFromJwtToken(token);
		String email = decodeEmailFromToken();
		User user = userRepository.findByEmail(email);
		if (user != null) {
			UserViewDTO userView = modelMapper.map(user, UserViewDTO.class);
			return userView;
		}
		return null;
	}

	@Override
	public String loginViaGmail(LoginRequest loginRequest) {
		String email = loginRequest.getEmail();
		String tokenGmail = loginRequest.getTokenGmail();

		User user = userRepository.findByEmail(email);
		if (user != null) {// CASE 1: Registered
			if (passwordEncoder.matches(tokenGmail, user.getPassword())) {
				return jwtProvider.generateToken(MyUserDetail.build(user));
			}
		} else { // CASE 2: UNREGISTERED
			User tempUser = new User();
			tempUser.setEmail(email);
			tempUser.setPassword(passwordEncoder.encode(tokenGmail));
			// Create wallet and set default balance
			Wallet wallet  = new Wallet();
			wallet.setBalance(new BigDecimal(300000));
			wallet.setUser(tempUser);
			tempUser.setWallet(wallet);
			// Set role
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleRepository.findByName(RoleName.ROLE_CUSTOMER));
			tempUser.setRoles(roles);
			// Save user
			User registeredUser = userRepository.save(tempUser);
			if (registeredUser != null) {
				return jwtProvider.generateToken(MyUserDetail.build(registeredUser));
			}
		}
		return null;
	}

	@Override
	public String loginViaUsername(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		// If authentication success
		// Set authentication information into SecurityHolder
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Create and send JWT back to user
		String jwt = jwtProvider.generateToken((MyUserDetail) authentication.getPrincipal());
		return jwt;
	}

}
