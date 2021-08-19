package com.example.restapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.DemoApplication;
import com.example.demo.model.Employee;
import com.example.demo.model.ListOfTaxthreshold;
import com.example.demo.model.Payslip;
import com.example.demo.model.Validation;
import com.example.demo.restcontroller.EmployeeController;
import com.example.demo.service.ListOfTaxthresholdService;
import com.example.demo.service.ListOfTaxthresholdServiceImpl;
import com.example.demo.service.PayslipService;
import com.example.demo.service.PayslipServiceImpl;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeControllerUnitTest {
	private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController()).build();;
	private String postEndPoint = "/postpayslip";
	private String str = "[{\"firstName\":\"David\",\"lastName\":\"Rudd\",\"annualSalary\":\"60050\",\"paymentMonth\":\"1\",\"superRate\":\"3\"},{\"firstName\":\"Ryan\",\"lastName\":\"Chen\",\"annualSalary\":\"120000\",\"paymentMonth\":\"1\",\"superRate\":\"5\"}]";
	
	@MockBean
	private ListOfTaxthreshold listOfTaxthreshold;
	
	@MockBean
	private Payslip payslip;
	
	@MockBean
	private Employee employee;
	
	@MockBean
	private Validation validation;
	
	@Test
	public void testResponse() throws Exception {		
		
	    mockMvc.perform(post(postEndPoint).contentType(MediaType.APPLICATION_JSON)
		        .content(str))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        
	}


	
}