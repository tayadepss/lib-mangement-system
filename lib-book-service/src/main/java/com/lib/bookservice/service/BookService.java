package com.lib.bookservice.service;

import java.util.List;

import com.lib.bookservice.dto.BookRequest;
import com.lib.bookservice.dto.BookResponse;
public interface BookService {

	String saveBook(BookRequest bookRequest);
	List<BookResponse> getAllBooks();
	BookResponse getBookById(Long id);

}
