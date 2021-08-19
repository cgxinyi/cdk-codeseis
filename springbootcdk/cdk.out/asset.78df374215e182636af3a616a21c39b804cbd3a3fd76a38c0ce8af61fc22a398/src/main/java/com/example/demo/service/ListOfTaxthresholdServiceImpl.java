package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ListOfTaxthreshold;
import com.example.demo.model.Taxthreshold;

@Service
public class ListOfTaxthresholdServiceImpl implements ListOfTaxthresholdService {
	
	private ListOfTaxthreshold listOfTaxthreshold = new ListOfTaxthreshold();

	@Override
	public ListOfTaxthreshold loadTaxthreshold()
	{
		listOfTaxthreshold.addTaxthreshold(0, 0, 0);
		listOfTaxthreshold.addTaxthreshold(18200, 0.19, 0);
		listOfTaxthreshold.addTaxthreshold(37000, 0.325, 3572);
		listOfTaxthreshold.addTaxthreshold(87000, 0.37, 19822);
		listOfTaxthreshold.addTaxthreshold(180000, 0.45, 54232);
		return listOfTaxthreshold;
	}
	
	@Override
	public ArrayList<Taxthreshold> getListOfTaxthreshold() {
		ArrayList<Taxthreshold> taxthresholds = listOfTaxthreshold.getListOfTaxthreshold();
		return taxthresholds;
	}
	
	
	@Override
	public int getNumberOfTaxthreshold()
	{
		return listOfTaxthreshold.getNumberOfTaxthreshold();
	}
	
	public Taxthreshold getTaxthreshold(int index)
    {
        return listOfTaxthreshold.getTaxthreshold(index);
    }
	

}
