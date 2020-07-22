package com.genpact.libraryservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genpact.libraryservice.model.Book;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long>{
	List<Book> findByLibraryId(Long libraryId);
}
