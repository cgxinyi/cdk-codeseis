package com.example.demo.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import com.example.demo.service.PayslipService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) 
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PayslipServiceUnit {
	
	@InjectMocks
	private PayslipService payslipService;
	
	@BeforeEach
	public void init() {
		payslipService = new PayslipServiceImpl();
	}
	
	@Test
    public void testCalculateIncomeTax1() {
		int calc = payslipService.calculateIncomeTax(0);
        assertEquals(calc,0);
    }
	
	@Test
    public void testCalculateIncomeTax2() {
		int calc = payslipService.calculateIncomeTax(20000);
        assertEquals(calc,342);
    }
	
	@Test
    public void testCalculateIncomeTax3() {
		int calc = payslipService.calculateIncomeTax(40000);
        assertEquals(calc,4547);
    }
	
	@Test
    public void testCalculateIncomeTax4() {
		int calc = payslipService.calculateIncomeTax(90000);
        assertEquals(calc,20932);
    }
	
	@Test
    public void testCalculateIncomeTax5() {
		int calc = payslipService.calculateIncomeTax(200000);
        assertEquals(calc,63232);
    }

}
