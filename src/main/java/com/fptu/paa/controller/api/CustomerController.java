package com.fptu.paa.controller.api;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.controller.request.LoginRequest;
import com.fptu.paa.controller.request.RechargeRequest;
import com.fptu.paa.dto.BikeRegisterDTO;
import com.fptu.paa.dto.BikeUpdateDTO;
import com.fptu.paa.dto.BikeViewDTO;
import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.entity.Bike;
import com.fptu.paa.service.BikeService;
import com.fptu.paa.service.TicketService;
import com.fptu.paa.service.TransactionService;
import com.fptu.paa.service.UserService;
import com.fptu.paa.utils.DateUtils;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/customer")
@Api(consumes = "application/json", description = "Customer API", tags = { "Customer" })
public class CustomerController {
	@Autowired
	BikeService bikeService;
	@Autowired
	TicketService ticketService;
	@Autowired
	UserService userService;
	@Autowired
	TransactionService transactionService;

	private ModelMapper modelMapper = new ModelMapper();

	@PostMapping("/login")
	public ResponseEntity<String> loginViaGmail(@RequestBody LoginRequest loginRequest) {
		String jwt = "";
		try {
			jwt = userService.loginViaGmail(loginRequest);
			if (jwt == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something wrong!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong credentials!");
		}
		return ResponseEntity.ok(jwt);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<String> updateUser(@RequestBody UserViewDTO userView) {
		UserViewDTO userViewDTO = userService.updateUserProfile(userView);
		if (userViewDTO != null) {
			return ResponseEntity.ok("Verify success!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verify failed!");
	}

	@PostMapping(value = "/recharge")
	public ResponseEntity<String> rechargeBalance(@RequestBody RechargeRequest rechargeRequest) {
		try {
			transactionService.saveTopUpTransaction(rechargeRequest.getUserID(), rechargeRequest.getAmount(),
					rechargeRequest.getDescription(), DateUtils.formattedDate(rechargeRequest.getCreateTime()));
			boolean recharge = userService.rechargeBalance(Long.valueOf(rechargeRequest.getUserID()),
					new BigDecimal(rechargeRequest.getAmount()));
			if (recharge) {
				return ResponseEntity.ok("Recharge success!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Recharge failed!");
	}

	@PostMapping(value = "/bike")
	public ResponseEntity<BikeViewDTO> registerBike(BikeRegisterDTO registerBike) {
		BikeViewDTO bike = null;
		try {
			Bike newBike = bikeService.registerBike(registerBike);
			if (newBike != null) {
				bike = modelMapper.map(newBike, BikeViewDTO.class);
				bike.setUserViewDTO(modelMapper.map(newBike.getUser(), UserViewDTO.class));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(bike);
	}

	@DeleteMapping(value = "/bike")
	public ResponseEntity<String> deleteBike(@RequestParam Long bikeId) {
		BikeViewDTO bike = bikeService.getBike(bikeId);
		if (bike.getStatus() != BikeStatus.CLAIMING && bike.getStatus() != BikeStatus.KEEPING) {
			bikeService.deleteBike(bikeId);
			bikeService.changeBikeStatus(bikeId, BikeStatus.UNVERIFIED);
		}
		return ResponseEntity.ok("Success");
	}

	@PutMapping(value = "/bike/update")
	public ResponseEntity<String> updateBike(@RequestBody BikeUpdateDTO bikeUpdate) throws Exception {
		Bike updateBike = bikeService.updateBike(bikeUpdate);
		if (updateBike != null) {
			return ResponseEntity.ok("Update success!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed!");
	}

	@GetMapping("")
	public ResponseEntity<UserViewDTO> getUserDetail(@RequestParam(defaultValue = "") Long userID) {
		UserViewDTO userViewDTO = null;
		if (userID != null) {
			userViewDTO = userService.getUserDetail(userID);
		} else
			userViewDTO = userService.getCurrentUser();
		if (userViewDTO != null) {
			return new ResponseEntity<UserViewDTO>(userViewDTO, HttpStatus.OK);
		}
		return new ResponseEntity<UserViewDTO>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/bike")
	public ResponseEntity<List<BikeViewDTO>> getBikesByUser() {
		UserViewDTO userView = userService.getCurrentUser();
		List<BikeViewDTO> bikeList = bikeService.getAllActiveBikeByUserid(userView.getId());
		if (bikeList != null && !bikeList.isEmpty()) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/bike/{id}")
	public ResponseEntity<BikeViewDTO> getBikeByID(@PathVariable Long id) {
		BikeViewDTO bike = bikeService.getBike(id);
		if (bike != null) {
			return new ResponseEntity<BikeViewDTO>(bike, HttpStatus.OK);
		}
		return new ResponseEntity<BikeViewDTO>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/bike/plate/{plateNumber}")
	public ResponseEntity<BikeViewDTO> getBikeByLicenseplate(@PathVariable String plateNumber) {
		BikeViewDTO bike = bikeService.getBikeByPlateNumber(plateNumber);
		if (bike != null) {
			return new ResponseEntity<BikeViewDTO>(bike, HttpStatus.OK);
		}
		return new ResponseEntity<BikeViewDTO>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/ticket/detail")
	public ResponseEntity<String> getTicketDetail(@RequestParam String checkInTime, @RequestParam String licensePlate) {
		String result = "No available ticket!";
		try {
			String ticketKey = "TICKET" + "_" + checkInTime + "_" + licensePlate;
			String tmp = ticketService.getTicketDetail(ticketKey);
			if (tmp != null) {
				result = tmp;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}
}
