package com.fptu.paa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@Column(name = "addrezz")
	@EqualsAndHashCode.Exclude
	private String address;

	@Column(name = "gender", length = 10)
	@EqualsAndHashCode.Exclude
	private String gender;

	@Column(name = "enabled", nullable = false, columnDefinition = "bit default 1")
	private boolean enabled = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	@EqualsAndHashCode.Exclude
	private Wallet wallet;
}
