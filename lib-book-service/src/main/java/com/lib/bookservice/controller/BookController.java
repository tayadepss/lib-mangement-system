package com.lib.bookservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.bookservice.dto.BookRequest;
import com.lib.bookservice.dto.BookResponse;
import com.lib.bookservice.service.BookService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/book")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name= "security_auth")
public class BookController {
	
	private final BookService bookService;

	@PostMapping(path="/save")
	public ResponseEntity<String> saveBook(@RequestBody @Validated BookRequest bookRequest){
		String msg=bookService.saveBook(bookRequest); 
		log.info("in BookController: book Saved Successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(msg);
	}
	@GetMapping(path="/getAllBooks")
	public ResponseEntity<List<BookResponse>> getAllBooks(){
		List<BookResponse> books= bookService.getAllBooks();
		return ResponseEntity.status(HttpStatus.OK).body(books);
	}
	@GetMapping(path="/getBookById/{id}")
	public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Long id){
		log.info("Book with id {} return successfully",id);
		return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id));
		
	}
}
