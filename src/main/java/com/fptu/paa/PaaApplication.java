package com.fptu.paa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.entity.Bike;
import com.fptu.paa.entity.Model;
import com.fptu.paa.entity.User;
import com.fptu.paa.repository.BikeRepository;
import com.fptu.paa.repository.ModelRepository;
import com.fptu.paa.repository.NFCRepository;
import com.fptu.paa.service.FareService;
import com.fptu.paa.service.ModelService;
import com.fptu.paa.service.NFCService;
import com.fptu.paa.service.RoleService;
import com.fptu.paa.service.TransmissionTypeService;
import com.fptu.paa.service.UserService;
import com.fptu.paa.utils.FabricUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class PaaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PaaApplication.class, args);

	}

	@Autowired
	UserService userService;
	@Autowired
	ModelService modelService;
	@Autowired
	RoleService roleService;
	@Autowired
	TransmissionTypeService transmissionTypeService;
	@Autowired
	FareService fareService;
	@Autowired
	NFCService nfcService;

	@Autowired
	BikeRepository bikeRepo;
	@Autowired
	NFCRepository nfcRepo;
	@Autowired
	ModelRepository modelRepo;
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
			roleService.insertDefaultRole();
			transmissionTypeService.initDefaultTransmissionType();
			modelService.initDefaultModels();
			fareService.initDefaultFare();
			User user = userService.insertDefaultAdmin();
			if (user != null)
				inputSampleBike(user);
			nfcService.initSampleNFC();
		}
	}

	private void inputSampleBike(User user) {
		Model model = modelRepo.findModelByBrandNameIgnoreCaseAndModelCodeIgnoreCaseAndVolume("honda", "lead", "125");
		if (model != null) {			
			Bike bike = new Bike(1L, "QuachTinh", "59P2-69096", "6328HZ256789", "Black-Grey", "", "",
					BikeStatus.PENDING, true, user, model);
			bikeRepo.save(bike);
		}
	}
}
