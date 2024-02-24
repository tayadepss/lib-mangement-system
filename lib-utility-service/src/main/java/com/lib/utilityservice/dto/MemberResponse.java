package com.lib.utilityservice.dto;


import java.time.LocalDate;

import com.lib.utilityservice.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
	
	private Long id;
	private String name;
	private Gender gender;	
	private LocalDate dateOfJoin;	
	private LocalDate dateOfBirth;	
	private Integer age;	
	private AddressResponse address;

}