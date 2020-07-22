package com.genpact.libraryservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.genpact.libraryservice.LibraryServiceIntegrationTest;
import com.genpact.libraryservice.dao.BookJpaRepository;

@RunWith(SpringRunner.class)
@LibraryServiceIntegrationTest
@DataJpaTest
public class BookServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Test
    public void testGetBook() {

    }

    @Test
    public void testAddBook() {

    }

    @Test
    public void testDeleteBook() {

    }
    
    @Test
    public void testUpdateBook() {

    }
}