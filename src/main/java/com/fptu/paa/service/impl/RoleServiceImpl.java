package com.fptu.paa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptu.paa.constant.RoleName;
import com.fptu.paa.entity.Role;
import com.fptu.paa.repository.RoleRepository;
import com.fptu.paa.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepo;

	@Override
	public void insertDefaultRole() {
		roleRepo.save(new Role(1L, RoleName.ROLE_STAFF, true));
		roleRepo.save(new Role(2L, RoleName.ROLE_CUSTOMER, true));
		roleRepo.save(new Role(3L, RoleName.ROLE_OWNER, true));
	}

}
