package com.genpact.libraryservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.genpact.libraryservice.model.Library;
import com.genpact.libraryservice.service.LibraryService;

@RestController
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	@GetMapping("/libraries")
	public List<Library> getAllLibrary(){
		return libraryService.getAllLibrary();
	} 
	
	@GetMapping("/libraries/{id}")
	public Library getLibrary(@PathVariable long id){
		return libraryService.getLibrary(id);
	} 
	
	@PostMapping("/libraries")
	public ResponseEntity<Void> addLibrary(@RequestBody Library library){

		long createdLibraryId = libraryService.addLibrary(library);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdLibraryId).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
