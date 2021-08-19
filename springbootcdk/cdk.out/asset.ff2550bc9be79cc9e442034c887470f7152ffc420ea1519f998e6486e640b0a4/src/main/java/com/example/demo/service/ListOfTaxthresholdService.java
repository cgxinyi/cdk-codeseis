package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.model.ListOfTaxthreshold;
import com.example.demo.model.Taxthreshold;

public interface ListOfTaxthresholdService {
	ListOfTaxthreshold loadTaxthreshold();
	ArrayList<Taxthreshold> getListOfTaxthreshold();
	int getNumberOfTaxthreshold();
	Taxthreshold getTaxthreshold(int index);
}
