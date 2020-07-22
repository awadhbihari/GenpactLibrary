package com.genpact.libraryservice.service;

import java.util.List;

import com.genpact.libraryservice.model.Book;

public interface BookService {
	
	Book getBook(long bookId);
	long addBook(Book book);
	Book updateBook(Book book);
	void deleteBook(long bookId);
	List<Book> getBooksByLibrary(long libraryId);
}
