package fi.haagahelia.harkka;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class RestinTesti {
	
	//Näiden avulla luodaan testiympäristö
	@Autowired
	private WebApplicationContext webAppContext;

	private MockMvc mockMvc;

	@BeforeEach //JUnit5
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	
	@Test //RestTuoteController
	public void statusOktuote() throws Exception {
		mockMvc.perform(get("/tuotteetrestjson")).andExpect(status().isOk());
	}
	
	@Test //RestTuoteController
	public void statusOktuoteluokka() throws Exception {
		mockMvc.perform(get("/tuoteluokatrestjson")).andExpect(status().isOk());
	}
	
	@Test //RestTuoteController
	public void responseTypeApplicationJson() throws Exception {
		mockMvc.perform(get("/tuotteetrestjson"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				.andExpect(status().isOk());
	}
	
	@Test //RestTuoteController
	public void responseTypeApplicationJsontuote() throws Exception {
		mockMvc.perform(get("/tuotteetrestjson/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE)) //väärä tietotyyppi
				.andExpect(status().isOk());
	}
	
	@Test //Spring rest
	public void apiStatusOktuote() throws Exception {
		mockMvc.perform(get("/api/tuotes")).andExpect(status().isOk());
	}
	
	@Test //Spring rest
	public void apiStatusOktuoteluokka() throws Exception {
		mockMvc.perform(get("/api/tuoteluokkas")).andExpect(status().isOk());
	}
	
	
	
	

}
