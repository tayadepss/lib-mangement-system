package com.lib.utilityservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.service.annotation.HttpExchange;

import com.lib.utilityservice.dto.MemberResponse;

@FeignClient(value="lib-member-service")
public interface MemberServiceClient {
	@GetMapping(path="/member/getMemberById/{id}",produces = "application/json")
	public MemberResponse getMemberById(@PathVariable("id") Long id);
}
