package com.fptu.paa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fptu.paa.entity.Role;
import com.fptu.paa.entity.User;
import com.fptu.paa.repository.RoleRepository;
import com.fptu.paa.repository.UserRepository;
import com.fptu.paa.utils.RoleName;

@SpringBootApplication
public class PaaApplication implements CommandLineRunner {
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(PaaApplication.class, args);
//		try {
//			FabricUtils.enrollAdmin();
//		} catch (Exception e) {
//			log.error("Enroll admin error. Message - {}", e.getMessage());
//		}
	}

	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// Add default admin into DB
		createRole();
		createDefaultAdmin();
	}

	private void createRole() {
		roleRepo.save(new Role(1L, RoleName.ROLE_ADMIN.name(), true));
		roleRepo.save(new Role(2L, RoleName.ROLE_USER.name(), true));
	}

	private void createDefaultAdmin() {
		if (!userRepo.existsById(1L)) {
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleRepo.findByName(RoleName.ROLE_ADMIN.name()));
			User admin = new User();
			admin.setEmail("admin@gmail.com");
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("1213"));
			admin.setRoles(roles);
			userRepo.save(admin);
			System.out.println(admin);
		}
	}
}
