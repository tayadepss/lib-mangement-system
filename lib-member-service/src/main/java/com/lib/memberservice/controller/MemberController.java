package com.lib.memberservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lib.memberservice.dto.MemberRequest;
import com.lib.memberservice.dto.MemberResponse;
import com.lib.memberservice.service.MemberService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/member")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name= "security_auth")
public class MemberController {
	
	private final MemberService memberService;

	@PostMapping(path="/save")
	public ResponseEntity<String> saveBook(@RequestBody @Validated MemberRequest memberRequest){
		String msg=memberService.saveMember(memberRequest); 
		log.info("in MemberController: member Saved Successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(msg);
	}
	@GetMapping(path="/getAllMembers")
	public ResponseEntity<List<MemberResponse>> getAllMembers(){
		List<MemberResponse> members= memberService.getAllmembers();
		return ResponseEntity.status(HttpStatus.OK).body(members);
	}
	@GetMapping(path="/getMemberById/{id}")
	public ResponseEntity<MemberResponse> getMemberById(@PathVariable("id") Long id){
		MemberResponse memberResponse=memberService.getMemberById(id);
		return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
		
	}
}
