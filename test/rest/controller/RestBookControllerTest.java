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



import rest.application.ApplicationInitializer;
import rest.application.security.WebConfig;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
		 mockMvc.perform(get("/rest/Book/279"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$", hasSize(1)))
		 .andExpect(jsonPath("$[0].bookName",anyOf(containsString("2GodFather"))))
		 .andExpect(jsonPath("$[0].authorName", is("Surma Bhopali")))
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
		 String bookName = "XXX1" + Math.random(); 
		
		 
		 
		 mockMvc.perform(post("/rest/Book/" + bookName + "/YYYY1"))
		 .andExpect(status().isOk())
		 ;

		 mockMvc.perform(post("/rest/Book").contentType(MediaType.APPLICATION_JSON).content("{\"bookId\":\"0\",\"bookName\":\"" + bookName + "\",\"authorName\":\"Surma Bhopali\",\"publisheddate\":\"2001-01-01\"}"))
		 .andExpect(status().isBadRequest())
		 ;
		 
	}
	
	@Test
	public void testBookUpdate() throws Exception {
		 mockMvc.perform(put("/rest/Book/279/1GodFather/XXX"))
		 .andExpect(status().isOk())
		 ;

		 mockMvc.perform(get("/rest/Book/279"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$", hasSize(1)))
		 .andExpect(jsonPath("$[0].bookName", is("1GodFather")))
		 .andExpect(jsonPath("$[0].authorName", is("XXX")))
		 ;		 

		 String url = "{\"bookId\":\"279\",\"bookName\":\"2GodFather\",\"authorName\":\"Surma Bhopali\",\"publisheddate\":\"2010-11-11\"}";
		 mockMvc.perform(put("/rest/Book").contentType(MediaType.APPLICATION_JSON).content(url))
		 .andExpect(status().isOk());

		 mockMvc.perform(get("/rest/Book/279"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$", hasSize(1)))
		 .andExpect(jsonPath("$[0].bookName", is("2GodFather")))
		 .andExpect(jsonPath("$[0].publisheddate", is("2010-11-11")));
		 
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
