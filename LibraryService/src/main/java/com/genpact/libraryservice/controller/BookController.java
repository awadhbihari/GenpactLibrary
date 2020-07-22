package com.genpact.libraryservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.genpact.libraryservice.model.Book;
import com.genpact.libraryservice.model.Library;
import com.genpact.libraryservice.service.BookService;
import com.genpact.libraryservice.service.LibraryService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	LibraryService libraryService;

	@GetMapping("/libraries/{libraryId}/books")
	public List<Book> getAllBooks(@PathVariable long libraryId){
		return bookService.getBooksByLibrary(libraryId);
	} 

	@GetMapping("/libraries/{libraryId}/books/{id}")
	public Book getBook(@PathVariable long id){
		return bookService.getBook(id);
	}

	@DeleteMapping("/libraries/{libraryId}/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable long id){
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/libraries/{libraryId}/books/{id}")
	public ResponseEntity<Book> updateTodo(@PathVariable (value = "libraryId") Long libraryId,
			@PathVariable(value = "id") long id, @RequestBody Book book){
		Library library = libraryService.getLibrary(libraryId);

		if(library == null) {
			throw new ResourceNotFoundException("Library " + libraryId + " not found");
		} 
		book.setLibrary(library);
		Book bookUpdated = bookService.updateBook(book);

		return new ResponseEntity<Book>(bookUpdated, HttpStatus.OK);
	}

	@PostMapping("/libraries/{libraryId}/books")
	public ResponseEntity<Void> createBook(@PathVariable (value = "libraryId") Long libraryId, @RequestBody Book book){

		Library library = libraryService.getLibrary(libraryId);
		if(library != null) {
			book.setLibrary(library);
			long createdBookId = bookService.addBook(book);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(createdBookId).toUri();

			return ResponseEntity.created(uri).build();
		} else {
			throw new ResourceNotFoundException("Library " + libraryId + " not found");
		}
	}

}
