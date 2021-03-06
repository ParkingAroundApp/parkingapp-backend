package com.fptu.paa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.dto.BikeRegisterDTO;
import com.fptu.paa.dto.BikeUpdateDTO;
import com.fptu.paa.dto.BikeViewDTO;
import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.entity.Bike;
import com.fptu.paa.entity.Model;
import com.fptu.paa.entity.User;
import com.fptu.paa.repository.BikeRepository;
import com.fptu.paa.repository.ModelRepository;
import com.fptu.paa.repository.UserRepository;
import com.fptu.paa.service.BikeService;
import com.fptu.paa.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BikeServiceImpl implements BikeService {

	@Autowired
	BikeRepository bikeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelRepository modelRepository;

	@Autowired
	UserService userService;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public Bike registerBike(BikeRegisterDTO bikeRegister) {
		Bike newBike = bikeRepository.findBikeByLicensePlateOrChassisNum(bikeRegister.getLicensePlate(),
				bikeRegister.getChassisNum());
		if (newBike == null) {
			User user = userRepository.findById(bikeRegister.getUserID()).get();
			if (user != null) {
				newBike = new Bike();
				// set user
				newBike.setUser(user);
				// Set image
				newBike.setFrontCertificateImage(bikeRegister.getFrontCertificateImage());
				newBike.setAfterCertificateImage(bikeRegister.getAfterCertificateImage());
				// Set model
				Model model = modelRepository.findModelById(bikeRegister.getModel_id());
				newBike.setModel(model);
				// Set other stuff
				newBike.setStatus(BikeStatus.PENDING);
				newBike.setOwnerName(bikeRegister.getOwnerName());
				newBike.setColor(bikeRegister.getColor());
				newBike.setLicensePlate(bikeRegister.getLicensePlate().toUpperCase());
				newBike.setChassisNum(bikeRegister.getChassisNum());
				// Save to db
				bikeRepository.save(newBike);
				return newBike;
			}
		}
		return null;
	}

	@Override
	public void deleteBike(Long bikeId) {
		bikeRepository.disableBike(bikeId);
	}

	@Override
	public BikeViewDTO changeBikeStatus(Long bike_id, BikeStatus bikeStatus) {
		Bike bike = bikeRepository.findBikeById(bike_id);
		if (bike != null) {
			bike.setStatus(bikeStatus);
			BikeViewDTO bikeView = modelMapper.map(bike, BikeViewDTO.class);
//			bikeView.setUser_id(bike.getUser().getId());
			bikeView.setUserViewDTO(modelMapper.map(bike.getUser(), UserViewDTO.class));
			return bikeView;
		}
		return null;
	}

	@Override
	public List<BikeViewDTO> getAllBikeByUserid(Long user_id) {
		List<BikeViewDTO> bikeViewList = null;
//		UserViewDTO userView = userService.getCurrentUser();
		try {
			List<Bike> bikeList = bikeRepository.findBikeByUser_id(user_id);
			if (bikeList != null) {
				java.lang.reflect.Type targetListType = new TypeToken<List<BikeViewDTO>>() {
				}.getType();
				bikeViewList = modelMapper.map(bikeList, targetListType);
				for (int i = 0; i < bikeViewList.size(); i++) {
//					bikeViewList.get(i).setUser_id(user_id);
//					bikeViewList.get(i).setUserViewDTO(modelMapper.map(bikeList.get(i).getUser(), UserViewDTO.class));
				}
			}
		} catch (Exception e) {
			log.debug("getAllBikeByUserid " + e.getMessage());
		}
		return bikeViewList;
	}

	@Override
	public List<BikeViewDTO> getAllActiveBikeByUserid(Long user_id) {
		List<BikeViewDTO> bikeViewList = null;
//		UserViewDTO userView = userService.getCurrentUser();
		try {
			List<Bike> bikeList = bikeRepository.findBikeByUser_idAndEnabled(user_id, true);
//			List<Bike> bikeList = new ArrayList<Bike>();
			if (bikeList != null) {
//				bikeList.removeIf(n -> (n.getStatus() == BikeStatus.UNVERIFIED));
				java.lang.reflect.Type targetListType = new TypeToken<List<BikeViewDTO>>() {
				}.getType();
				bikeViewList = modelMapper.map(bikeList, targetListType);
				for (int i = 0; i < bikeViewList.size(); i++) {
//					bikeViewList.get(i).setUser_id(user_id);
					bikeViewList.get(i).setUserViewDTO(modelMapper.map(bikeList.get(i).getUser(), UserViewDTO.class));
				}
			}
//			for (int i = 0; i < bikeViewList.size(); i++) {
//				bikeViewList.get(i).setUser_id(user_id);
//			}
		} catch (Exception e) {
			log.debug("getAllActiveBikeByUserid " + e.getMessage());
		}
		return bikeViewList;
	}

	@Override
	public List<BikeViewDTO> getAllBikesByStatus(BikeStatus status) {
		List<BikeViewDTO> bikeViewList = null;
		try {
			List<Bike> bikeList = bikeRepository.findBikeByStatus(status);
			if (bikeList != null) {
				java.lang.reflect.Type targetListType = new TypeToken<List<BikeViewDTO>>() {
				}.getType();
				bikeViewList = modelMapper.map(bikeList, targetListType);
			}
			for (int i = 0; i < bikeViewList.size(); i++) {
//				bikeViewList.get(i).setUser_id(bikeList.get(i).getUser().getId());
				bikeViewList.get(i).setUserViewDTO(modelMapper.map(bikeList.get(i).getUser(), UserViewDTO.class));
			}
		} catch (Exception e) {
			log.debug("getAllBikesByStatus " + e.getMessage());
		}
		return bikeViewList;
	}

	@Override
	public BikeViewDTO getBikeByPlateNumber(String plateNumber) {
		Bike bike = bikeRepository.findBikeByLicensePlate(plateNumber);
		if (bike != null) {
			BikeViewDTO bikeView = modelMapper.map(bike, BikeViewDTO.class);
//			bikeView.setUser_id(bike.getUser().getId());
			bikeView.setUserViewDTO(modelMapper.map(bike.getUser(), UserViewDTO.class));
			return bikeView;
		}
		return null;
	}

	@Override
	public BikeViewDTO getBike(Long bike_id) {
		Bike bike = bikeRepository.findBikeById(bike_id);
		if (bike != null) {
			BikeViewDTO bikeView = modelMapper.map(bike, BikeViewDTO.class);
//			bikeView.setUser_id(bike.getUser().getId());
			bikeView.setUserViewDTO(modelMapper.map(bike.getUser(), UserViewDTO.class));
			return bikeView;
		}
		return null;
	}

	@Override
	public boolean checkIn(Long bikeId) {
		Bike bike = bikeRepository.findBikeById(bikeId);
		if (bike != null) {
			if (bike.getStatus() != BikeStatus.KEEPING) {
				if (bike.getStatus() == BikeStatus.VERIFIED || bike.getStatus() == BikeStatus.FINISH) {
					bike.setStatus(BikeStatus.KEEPING);
					bike = bikeRepository.save(bike);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Bike updateBike(BikeUpdateDTO bikeUpdate) throws Exception {
		Bike bike = bikeRepository.findBikeById(bikeUpdate.getId());
		User user = userRepository.findById(bikeUpdate.getUserID()).get();
		Model model = modelRepository.findModelById((bikeUpdate.getModel_id()));
		if (bike != null) {			
			bike = modelMapper.map(bikeUpdate, Bike.class);
			bike.setUser(user);
			bike.setModel(model);
			bike.setStatus(BikeStatus.PENDING);
			bikeRepository.save(bike);
			return bike;
		}
		return null;
	}

}
