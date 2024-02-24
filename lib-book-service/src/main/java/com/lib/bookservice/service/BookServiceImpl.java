package com.lib.bookservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lib.bookservice.dto.BookInfoResponse;
import com.lib.bookservice.dto.BookRequest;
import com.lib.bookservice.dto.BookResponse;
import com.lib.bookservice.model.Book;
import com.lib.bookservice.model.BookInfo;
import com.lib.bookservice.model.Category;
import com.lib.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	
	private final ModelMapper modelMapper;
	private final BookRepository bookRepository;
	
	@Override
	public String saveBook(BookRequest bookRequest) {
		Book book=new Book();
		BookInfo bookInfo=new BookInfo();
		book=modelMapper.map(bookRequest,Book.class);
		bookInfo=modelMapper.map(bookRequest.getInfo(),BookInfo.class);
		
		log.info("book category:{}",bookInfo.getCategory());
		book.setInfo(bookInfo);
		book=bookRepository.save(book);
		log.info("book saved with id:{}",book.getId());
		
		return "Book with Book id:"+book.getId()+" saved successfully";
	}

	@Override
	public List<BookResponse> getAllBooks() {
		List<Book> books=bookRepository.findAll();
		List<BookResponse> booksResponse=books.stream().map(book->mapToBookResponse(book)).collect(Collectors.toList());
		log.info("return List of {} books",booksResponse.size());
		return booksResponse;
	}

	private BookResponse mapToBookResponse(Book book) {
		BookResponse bookResponse=modelMapper.map(book, BookResponse.class);
		bookResponse.setInfo(modelMapper.map(book.getInfo(),BookInfoResponse.class));
		return bookResponse;
	}

	@Override
	public BookResponse getBookById(Long id) {
		BookResponse bookResponse=new BookResponse();
		Book book=bookRepository.findById(id).get();
		bookResponse=modelMapper.map(book, BookResponse.class);
		bookResponse.setInfo(modelMapper.map(book.getInfo(),BookInfoResponse.class));
		log.info("book with book name:{} return",bookResponse.getName());
		return bookResponse;
	}

}
