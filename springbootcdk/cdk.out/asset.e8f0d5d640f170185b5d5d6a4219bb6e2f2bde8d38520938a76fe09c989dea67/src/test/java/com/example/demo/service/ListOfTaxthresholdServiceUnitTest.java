package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import com.example.demo.model.ListOfTaxthreshold;
import com.example.demo.model.Taxthreshold;

public class ListOfTaxthresholdServiceUnitTest {

	@InjectMocks
	private ListOfTaxthresholdService listOfTaxthresholdService;
	
	@BeforeEach
	public void init() {
		listOfTaxthresholdService = new ListOfTaxthresholdServiceImpl();
	}
	
	@Test
    public void testLoadTaxthreshold() {
		ListOfTaxthreshold listOfTaxthreshold = listOfTaxthresholdService.loadTaxthreshold();
		Assertions.assertNotNull(listOfTaxthreshold);
    }
	
	@Test
	public void testGetListOfTaxthresholdIsEmpty() {
		ArrayList<Taxthreshold> taxthreshold = listOfTaxthresholdService.getListOfTaxthreshold();
		assertEquals(true, taxthreshold.isEmpty());

	}
	
}
