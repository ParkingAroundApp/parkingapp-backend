package com.fptu.paa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.constant.NFCStatus;
import com.fptu.paa.constant.RoleName;
import com.fptu.paa.entity.Bike;
import com.fptu.paa.entity.Model;
import com.fptu.paa.entity.NFC;
import com.fptu.paa.entity.Role;
import com.fptu.paa.entity.User;
import com.fptu.paa.repository.BikeRepository;
import com.fptu.paa.repository.NFCRepository;
import com.fptu.paa.repository.RoleRepository;
import com.fptu.paa.repository.UserRepository;
import com.fptu.paa.utils.FabricUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class PaaApplication implements CommandLineRunner {
//	@Value("${paa.app.aslocalhost}")
//	static String asLocalHost;

//	static {
//		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
//	}

	public static void main(String[] args) {
		SpringApplication.run(PaaApplication.class, args);

	}

	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	BikeRepository bikeRepo;
	@Autowired
	NFCRepository nfcRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Value("${paa.app.InsertSample}")
	private boolean insertSample;
	@Value("${paa.app.aslocalhost}")
	String asLocalHost;

	@Override
	public void run(String... args) throws Exception {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", asLocalHost);
		try {
			FabricUtils.enrollAdmin();
		} catch (Exception e) {
			log.error("Enroll admin error. Message - {}", e.getMessage());
		}

		// Add sample admin and bike to DB
		if (insertSample) {
			createRole();
			User user = createDefaultAdmin();
			if (user != null)
				inputSampleBike(user);
			inputSampleNFC();
		}
	}

	private void createRole() {
		roleRepo.save(new Role(1L, RoleName.ROLE_STAFF, true));
		roleRepo.save(new Role(2L, RoleName.ROLE_CUSTOMER, true));
		roleRepo.save(new Role(3L, RoleName.ROLE_OWNER, true));
	}

	private User createDefaultAdmin() {
		User result = null;
		if (!userRepo.existsById(1L)) {
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleRepo.findByName(RoleName.ROLE_OWNER));
			User admin = new User();
			admin.setEmail("admin@gmail.com");
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("1213"));
			admin.setRoles(roles);
			result = userRepo.save(admin);
			System.out.println(admin);
		}
		return result;
	}

	private void inputSampleBike(User user) {
		Model model = new Model(1L, "Honda", "Airblade", "Scooter", "125cc", true);
		Bike bike = new Bike(1L, "QuachTinh", "59P2-69096", "6328HZ256789", "Black-Grey", "", "", BikeStatus.PENDING,
				true, user, model);
		bikeRepo.save(bike);
	}

	private void inputSampleNFC() {
		NFC nfc = new NFC(1L, "123456789", NFCStatus.FINISH, true);
		nfcRepo.save(nfc);
	}
}
