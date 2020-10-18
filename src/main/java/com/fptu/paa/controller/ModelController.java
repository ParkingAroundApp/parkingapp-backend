package com.fptu.paa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.ModelStatus;
import com.fptu.paa.dto.BikeViewDTO;
import com.fptu.paa.dto.ModelRegistrationDTO;
import com.fptu.paa.entity.Model;
import com.fptu.paa.repository.BikeRepository;
import com.fptu.paa.repository.ModelRepository;
import com.fptu.paa.service.BikeService;
import com.fptu.paa.service.ModelService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/model")
@Api(consumes = "application/json")
public class ModelController {
@Autowired
ModelRepository modelRepository;
@Autowired
ModelService modelService;

@GetMapping(value = "{id}")
public ResponseEntity<Model> getModel(@PathVariable Long id ) {
	Model model = modelRepository.findModelById(id);
	if(model!= null) {
		return new ResponseEntity<Model>(model,HttpStatus.OK);
	}
	return new ResponseEntity<Model>(HttpStatus.BAD_REQUEST);
}
@GetMapping(value = "all")
public ResponseEntity<List<Model>> getAllModels() {
	List<Model> model = modelRepository.findAll();
	if(model!= null) {
		return new ResponseEntity<List<Model>>(model,HttpStatus.OK);
	}
	return new ResponseEntity<List<Model>>(HttpStatus.BAD_REQUEST);
}
@GetMapping(value = "activeAll")
public ResponseEntity<List<Model>> getAllActiveModels() {
	List<Model> model = modelRepository.findModelByStatus(ModelStatus.ENABLED);
	if(model!= null) {
		return new ResponseEntity<List<Model>>(model,HttpStatus.OK);
	}
	return new ResponseEntity<List<Model>>(HttpStatus.BAD_REQUEST);
}
@GetMapping(value = "status")
public ResponseEntity<List<Model>> getAllModelByStatus(ModelStatus status) {
	List<Model> model = modelRepository.findModelByStatus(status);
	if(model!= null) {
		return new ResponseEntity<List<Model>>(model,HttpStatus.OK);
	}
	return new ResponseEntity<List<Model>>(HttpStatus.BAD_REQUEST);
}

@PostMapping(value = "insert")
public ResponseEntity<ModelRegistrationDTO> insertModel(ModelRegistrationDTO modelRegistration){
	ModelRegistrationDTO modelRegistrationDTO = modelService.insertNewModel(modelRegistration);
	if (modelRegistrationDTO!=null) {
		return new ResponseEntity<ModelRegistrationDTO>(modelRegistrationDTO, HttpStatus.OK);
	}
	return new ResponseEntity<ModelRegistrationDTO>(HttpStatus.BAD_REQUEST);
}



}
