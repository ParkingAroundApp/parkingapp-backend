package com.fptu.paa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fptu.paa.constant.BikeStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Bikes", schema = "parkingdb")
public class Bike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ownerName", length = 30)
	private String ownerName;

	@Column(name = "licensePlate", unique = true, length = 10)
	private String licensePlate;

	@Column(name = "chassisNum", unique = true, length = 30)
	private String chassisNum;

	@Column(name = "color", length = 16)
	private String color;

	@Column(name = "status", length = 16)
	@Enumerated(EnumType.STRING)
	private BikeStatus status;

	@Column(name = "enabled")
	private boolean enabled = true;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "model_id")
	private Model model;
}
