package com.lib.utilityservice.service;

import com.lib.utilityservice.dto.BookOrderRequest;
import com.lib.utilityservice.dto.MemberWithBooks;

public interface LibraryUtilityService {

	String save(BookOrderRequest order);

	MemberWithBooks getMemberWithBooks(Long id);

}
