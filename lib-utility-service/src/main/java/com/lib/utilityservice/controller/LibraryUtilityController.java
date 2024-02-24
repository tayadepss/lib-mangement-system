package com.lib.utilityservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.utilityservice.dto.BookOrderRequest;
import com.lib.utilityservice.dto.MemberWithBooks;
import com.lib.utilityservice.service.LibraryUtilityService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/utility")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name= "security_auth")
public class LibraryUtilityController {
	
	private final LibraryUtilityService libraryUtilityService;
	
	@PostMapping(path="/save")
	public ResponseEntity<String> save(@RequestBody BookOrderRequest order){	
		String msg=libraryUtilityService.save(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(msg);
	}
	
	@GetMapping(path="/getMemberWithBooks/{id}")
	public ResponseEntity<MemberWithBooks> getMemberWithBooks(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(libraryUtilityService.getMemberWithBooks(id));
	}
	
	
}
