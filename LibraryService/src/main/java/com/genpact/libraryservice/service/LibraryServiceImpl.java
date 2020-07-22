package com.genpact.libraryservice.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.genpact.libraryservice.dao.LibraryJpaRepository;
import com.genpact.libraryservice.model.Library;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryJpaRepository libraryJpaRepository;

	@Autowired
	@Qualifier("listLibrary")
	 private List<String> libraries;	
	
	@PostConstruct
	public void init() {
		for(String library : libraries)
			addLibrary(new Library(library, library +" Library"));
	}
	
	@Override
	public List<Library> getAllLibrary() {
		return libraryJpaRepository.findAll();
	}

	@Override
	public long addLibrary(Library library) {
		Library createdLibrary = libraryJpaRepository.save(library);
		return createdLibrary.getId();
	}

	@Override
	public Library getLibrary(long libraryId) {
		return libraryJpaRepository.findById(libraryId).get();
	}

}
