package com.projectone.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name = "employee", schema = "public")
@Data

public  class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long userId;
	@Column (name = "firstname")
	private String firstName;
	@Column (name = "lastname")
	private String lastName;
	@Column (name = "username")
	private String username;
	@Column (name= "email")
	private String email;
	@Column (name="password")
	private String password;
	@Column (name = "dob")
	private String dob;
	@Column (name = "aboutme")
	private String aboutMe;
	@Column (name="authorid")
	private int authorid;
}
