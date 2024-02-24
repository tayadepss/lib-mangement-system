package com.lib.bookservice.dto;

import com.lib.bookservice.model.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookInfoResponse {
	private Long id;
	private String author;
	private Long pages;
	private Category category;
	

}
