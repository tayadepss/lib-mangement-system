package com.lib.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
