package com.genpact.libraryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.libraryservice.dao.BookJpaRepository;
import com.genpact.libraryservice.model.Book;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookJpaRepository bookJpaRepository;
	
	@Override
	public Book getBook(long bookId) {
		return bookJpaRepository.findById(bookId).get();
	}

	@Override
	public long addBook(Book book) {
		Book createdBook = bookJpaRepository.save(book);
		return createdBook.getId();
	}

	@Override
	public Book updateBook(Book book) {
		return bookJpaRepository.save(book);		
	}

	@Override
	public void deleteBook(long bookId) {
		bookJpaRepository.deleteById(bookId);		
	}

	@Override
	public List<Book> getBooksByLibrary(long libraryId) {
		return bookJpaRepository.findByLibraryId(libraryId);
	}

}
