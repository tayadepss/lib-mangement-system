package com.lib.utilityservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lib.utilityservice.clients.BookServiceClient;
import com.lib.utilityservice.clients.MemberServiceClient;
import com.lib.utilityservice.dto.BookOrderRequest;
import com.lib.utilityservice.dto.BookResponse;
import com.lib.utilityservice.dto.MemberResponse;
import com.lib.utilityservice.dto.MemberWithBooks;
import com.lib.utilityservice.model.LibraryUtility;
import com.lib.utilityservice.repository.LibraryUtilityRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LibraryUtilityServiceImpl implements LibraryUtilityService {

	private final MemberServiceClient memberServiceClient;
	private final LibraryUtilityRepository libraryUtilityRepository;
	private final ModelMapper modelMapper;
	private final BookServiceClient bookServiceClient;

	@Override
	public String save(BookOrderRequest order) {		
		libraryUtilityRepository.save(modelMapper.map(order, LibraryUtility.class));		
		return "Book order submited successfully";
	}
	@Override
	public MemberWithBooks getMemberWithBooks(Long id) {
		LibraryUtility libRec= libraryUtilityRepository.findByMemberId(id).get();
		List<Long> booksId= Arrays.asList(libRec.getBooksId().split(",")).stream().map(id0->Long.parseLong(id0)).toList();
		log.info("memberId:{}",booksId);

		MemberResponse member=memberServiceClient.getMemberById(libRec.getMemberId());
		List<BookResponse> books=booksId.stream().map(id1->bookServiceClient.getBookById(id1)).toList();
		MemberWithBooks memberWithBooks=new MemberWithBooks();
		memberWithBooks.setMemberName(member.getName());
		memberWithBooks.setBookNames(books.stream().map(book->book.getName()).toList());
		
		return memberWithBooks;
	}

}
