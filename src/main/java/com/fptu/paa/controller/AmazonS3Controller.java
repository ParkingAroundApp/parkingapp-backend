package com.fptu.paa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fptu.paa.service.AmazonS3Service;


@RestController
@RequestMapping("/amazon/s3")
public class AmazonS3Controller {
	private AmazonS3Service amazonS3Service;
	
	@Autowired
	public AmazonS3Controller(AmazonS3Service amazonS3Service) {
		this.amazonS3Service = amazonS3Service;
	}
	
	
	@PostMapping(value = "/uploadFiles", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadFiles(@RequestPart(value = "file") MultipartFile[] images)  {
		List<String> fileNames = new ArrayList<String>();
		for(MultipartFile image : images) {
			fileNames.add(amazonS3Service.uploadFile(image));
		}
        return ResponseEntity.ok(fileNames);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return amazonS3Service.deleteFileFromS3Bucket(fileUrl);
    }

    @GetMapping("/images/{name_image}")
    public ResponseEntity<byte[]> getImage(@PathVariable("name_image") String nameImage) {
        return ResponseEntity.ok().contentType(MediaType.valueOf(AmazonS3Service.CONTENT_TYPE_IMAGE)).body(amazonS3Service.getImage(nameImage));
    }
}
