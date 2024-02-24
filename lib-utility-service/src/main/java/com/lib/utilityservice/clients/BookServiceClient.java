package com.lib.utilityservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.service.annotation.HttpExchange;

import com.lib.utilityservice.dto.BookResponse;

@FeignClient(value="lib-book-service")
public interface BookServiceClient {

	@GetMapping(path="/book/getBookById/{id}",produces = "application/json")
	public BookResponse getBookById(@PathVariable("id") Long id);
}
