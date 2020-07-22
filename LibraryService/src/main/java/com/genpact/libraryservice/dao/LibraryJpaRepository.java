package com.genpact.libraryservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.libraryservice.model.Library;

public interface LibraryJpaRepository extends JpaRepository<Library, Long>{
}
