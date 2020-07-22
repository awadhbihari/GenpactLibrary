package com.genpact.libraryservice.service;

import java.util.List;

import com.genpact.libraryservice.model.Library;

public interface LibraryService {

	List<Library> getAllLibrary();
	long addLibrary(Library library);
	Library getLibrary(long libraryId);
}
