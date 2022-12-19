package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {
	MockMvc mockMvc;
	@Mock
	private BalloonService balloonService;
	@BeforeEach
	public void setup(WebApplicationContext wac)
	{
		this.mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();

	}
	@Test
	void contextLoads() {
	}
	@Test
	public void getTestProduct() throws Exception {
		List<Balloon> balloonList=this.balloonService.listAll();
		MockHttpServletRequestBuilder productRequest= MockMvcRequestBuilders.get("/balloons");
		this.mockMvc.perform(productRequest)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attribute("balloons",balloonList))
				.andExpect(MockMvcResultMatchers.view().name("listBalloons"));

	}
}
