package rest.controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import rest.application.ApplicationInitializer;
import rest.application.security.WebConfig;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.core.AnyOf;

//Spring Rest Annotations
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, ApplicationInitializer.class})

public class RestBookControllerTest {

	@Autowired
    private WebApplicationContext wac;
	
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	
	@Test
	public void testBookListForId() throws Exception {
		 mockMvc.perform(get("/rest/Book/276"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$", hasSize(1)))
		 .andExpect(jsonPath("$[0].bookName",anyOf(containsString("GodFather"))))
		 .andExpect(jsonPath("$[0].authorName", is("Mario P")))
		 ;
	}
	
	@Test
	public void testBookList() throws Exception {
		 mockMvc.perform(get("/rest/Book"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$..bookName",hasItems("GodFather")))
		 .andExpect(jsonPath("$", hasSize(greaterThan(1))))
		 ;
	}
	
	@Test
	public void testBookAdd() throws Exception {
		 mockMvc.perform(post("/rest/Book/XXX1/YYYY1"))
		 .andExpect(status().isOk())
		 ;
		 
		 mockMvc.perform(post("/rest/Book").contentType(MediaType.APPLICATION_JSON).content("{\"bookId\":\"0\",\"bookName\":\"Eye of Tiger\",\"authorName\":\"Surma Bhopali\"}"))
		 .andExpect(status().isOk())
		 ;
		 
	}
	
	@Test
	public void testBookUpdate() throws Exception {
		 mockMvc.perform(put("/rest/Book/276/1GodFather/XXX"))
		 .andExpect(status().isOk())
		 ;

		 mockMvc.perform(get("/rest/Book/276"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$", hasSize(1)))
		 .andExpect(jsonPath("$[0].bookName", is("1GodFather")))
		 .andExpect(jsonPath("$[0].authorName", is("XXX")))
		 ;		 
		 
		 mockMvc.perform(put("/rest/Book/276/GodFather/Mario P"))
		 .andExpect(status().isOk())
		 ;
		 
	}

	@Test
	public void testBookDelete() throws Exception {
		 mockMvc.perform(delete("/rest/Book/1"))
		 .andExpect(status().isOk())
		 ;
	}	
	
	
	@Test
	public void testSearchBookByName() throws Exception {
		 mockMvc.perform(get("/rest/Book/Search/BookName/GodFather"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$..bookName",hasItems("GodFather")))
		 .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
		 ;
	}	

	
}
