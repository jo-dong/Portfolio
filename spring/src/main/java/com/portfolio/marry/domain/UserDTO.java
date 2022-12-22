package com.portfolio.marry.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Entity
@Table(name = "userinfo")
public class UserDTO {
	
	// id : Primary Key
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	// user ID
	@Column(name = "userid")
	private String userId;
	
	// user password
	@Column(name = "userpw")
	private String userPw;
	
	// user name
	@Column(name = "username")
	private String userName;
	
	// user age
	@Column(name = "userage")
	private Integer userAge;
	
	// gender
	@Column(name = "gender")
	private String gender;
	
	// user phone number
	@Column(name = "phonenum")
	private String phoneNum;
	
	// user MBTI
	@Column(name = "mbti")
	private String mbti;
	
	// user address
	@Column(name = "address")
	private String address;

	public UserDTO(String userId, String userPw, String userName, 
				   Integer userAge, String gender, String phoneNum,
				   String mbti, String address) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userAge = userAge;
		this.gender = gender;
		this.phoneNum = phoneNum;
		this.mbti = mbti;
		this.address = address;
	}
	
	
}
