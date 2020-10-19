package com.fptu.paa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.dto.BikeRegisterDTO;
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
import com.sun.media.jfxmedia.logging.Logger;

@Service
@Transactional
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
	public BikeRegisterDTO registerBike(BikeRegisterDTO bikeRegister) {
		Bike bike = null;
		 UserViewDTO userView = userService.getCurrentUser();
		 User user = userRepository.findUserById(userView.getId());
		 Model model = modelRepository.findModelById(bikeRegister.getModel_id());
		
		try {
			if (bikeRegister != null) {
				bike = modelMapper.map(bikeRegister, Bike.class);
				bike.setUser(user);
				bike.setModel(model);
				bike.setStatus(BikeStatus.PENDING);
				bike = bikeRepository.save(bike);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bikeRegister;
	}

	@Override
	public Bike approveBike(BikeStatus bikeStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bike deleteBike(BikeStatus bikeStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BikeViewDTO changeBikeStatus(Long bike_id, BikeStatus bikeStatus) {
		Bike bike = bikeRepository.findBikeById(bike_id);
		if(bike!=null) {
			bike.setStatus(bikeStatus);
			BikeViewDTO bikeView = modelMapper.map(bike, BikeViewDTO.class);
			bikeView.setUser_id(bike.getUser().getId());
			return bikeView;
		}
		return null;
	}

	@Override
	public List<BikeViewDTO> getAllBikeByUserid(Long user_id) {
		List<BikeViewDTO> bikeViewList =null;
//		UserViewDTO userView = userService.getCurrentUser();
		try {
			List<Bike>bikeList = bikeRepository.findBikeByUser_id(user_id);
			if(bikeList!=null) {
				java.lang.reflect.Type targetListType = new TypeToken<List<BikeViewDTO>>() {
                }.getType();
                bikeViewList= modelMapper.map(bikeList,targetListType);
               for (int i=0;i<bikeViewList.size();i++) {
            	   bikeViewList.get(i).setUser_id(user_id);    	   
               }
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bikeViewList;
	}

	@Override
	public List<BikeViewDTO> getAllActiveBikeByUserid(Long user_id) {
		List<BikeViewDTO> bikeViewList =null;
//		UserViewDTO userView = userService.getCurrentUser();
		try {
			List<Bike>bikeList = bikeRepository.findBikeByUser_idAndStatus(user_id, BikeStatus.ENABLED);
			if(bikeList!=null) {
				java.lang.reflect.Type targetListType = new TypeToken<List<BikeViewDTO>>() {
                }.getType();
                bikeViewList= modelMapper.map(bikeList,targetListType);
			}
			 for (int i=0;i<bikeViewList.size();i++) {
          	   bikeViewList.get(i).setUser_id(user_id);    	   
             }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bikeViewList;
	}

	@Override
	public List<BikeViewDTO> getAllBikesByStatus(BikeStatus status) {
		List<BikeViewDTO> bikeViewList =null;
		try {
			List<Bike>bikeList = bikeRepository.findBikeByStatus(status);
			if(bikeList!=null) {
				java.lang.reflect.Type targetListType = new TypeToken<List<BikeViewDTO>>() {
                }.getType();
                bikeViewList= modelMapper.map(bikeList,targetListType);
			}
			 for (int i=0;i<bikeViewList.size();i++) {
          	   bikeViewList.get(i).setUser_id(bikeList.get(i).getUser().getId());    	   
             }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bikeViewList;
	}

	@Override
	public BikeViewDTO getBikeByPlateNumber(String plateNumber) {
		Bike bike = bikeRepository.findBikeByLicensePlate(plateNumber);
		if(bike!=null) {
			BikeViewDTO bikeView = modelMapper.map(bike, BikeViewDTO.class);
			bikeView.setUser_id(bike.getUser().getId());
			return bikeView;
		}
		return null;
	}

	@Override
	public BikeViewDTO getBike(Long bike_id) {
		Bike bike = bikeRepository.findBikeById(bike_id);
		if(bike!=null) {
			BikeViewDTO bikeView = modelMapper.map(bike, BikeViewDTO.class);
			bikeView.setUser_id(bike.getUser().getId());
			return bikeView;
		}
		return null;
	}


}
