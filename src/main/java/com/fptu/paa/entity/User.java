package com.fptu.paa.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users", schema = "parkingdb")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Email
	@Column(name = "email")
	private String email;

	@Column(name = "phone", length = 16)
	private String phone;

	@Column(name = "birthday", length = 30)
	@EqualsAndHashCode.Exclude
	private String birthday;

	@Column(name = "address")
	@EqualsAndHashCode.Exclude
	private String address;

	@Column(name = "gender", length = 10)
	@EqualsAndHashCode.Exclude
	private String gender;

	@Column(name = "balance")
	private BigDecimal balance = new BigDecimal("0.0");

	@Column(name = "enabled")
	private boolean enabled = true;

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(schema = "parkingdb", name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles = new HashSet<>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;
}
