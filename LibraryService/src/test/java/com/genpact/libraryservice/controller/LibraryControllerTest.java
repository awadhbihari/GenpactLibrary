package com.genpact.libraryservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.genpact.libraryservice.SpringConf;
import com.genpact.libraryservice.model.Library;
import com.genpact.libraryservice.service.LibraryService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LibraryControllerTest.TestConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@PropertySource("classpath:application.properties")
public class LibraryControllerTest {
	
	@Configuration
	@ComponentScan(basePackageClasses = SpringConf.class)
	public static class TestConfig {
		private Map<Long, Library> data = new HashMap<>();
		private long idCounter =0;
		@Bean
		public LibraryService libraryService() {
			return new LibraryService () {

				@Override
				public List<Library> getAllLibrary() {
					return new ArrayList<Library>(data.values());
				}

				@Override
				public long addLibrary(Library library) {
					library.setId(++idCounter);
					data.put(library.getId(), library);
					return library.getId();
				}

				@Override
				public Library getLibrary(long libraryId) {
					return data.get(libraryId);
				}
				
			};
		}
	}

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	LibraryService libraryService;

	private MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetAllLibraryWithNoLibraryInDatabase() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/libraries"))
		.andExpect(jsonPath("$.length()").value(0));
	}

	@Test
	public void testGetAllLibraryWithMultipleLibraryInDatabase() throws Exception {
		libraryService.addLibrary(new Library("State", "State Library"));
		libraryService.addLibrary(new Library("District", "District Library"));
		libraryService.addLibrary(new Library("Town", "Town Library"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/libraries"))
		.andExpect(jsonPath("$.length()").value(3));
	}
	
	@Test
	public void testGetLibrary() throws Exception {
		libraryService.addLibrary(new Library("State", "State Library"));

		mockMvc.perform(MockMvcRequestBuilders.get("/libraries/1"))
		.andExpect(jsonPath("$.libraryName").value("State"));
	}

	
	@Test
	public void afterAddingLibraryItCanBeFound() throws Exception {
		mockMvc.perform(post("/libraries").content("{\"id\": -1,\"libraryName\": \"My local\",\"description\": \"Local lib\"}").contentType("application/json"))
		.andExpect(status().isOk());

		mockMvc.perform(MockMvcRequestBuilders.get("/libraries/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.libraryName").value("My local"));
	}
}
