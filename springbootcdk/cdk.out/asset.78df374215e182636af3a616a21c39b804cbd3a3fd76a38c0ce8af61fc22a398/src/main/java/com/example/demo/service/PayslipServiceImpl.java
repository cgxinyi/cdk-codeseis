package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ListOfTaxthreshold;
import com.example.demo.model.Payslip;

@Service
public class PayslipServiceImpl implements PayslipService {
	

	private ListOfTaxthresholdService listOfTaxthresholdService = new ListOfTaxthresholdServiceImpl();
	
	private Payslip payslip;
	
	@Override
	public int calculateIncomeTax(int grossIncome)
	{
		int incomeTax=0;
		listOfTaxthresholdService.loadTaxthreshold();
		int rangeCheck=1;
		for(int i=0;i<listOfTaxthresholdService.getNumberOfTaxthreshold();i++)
		{
			int lastIndex = listOfTaxthresholdService.getNumberOfTaxthreshold()-1;
			int calc = grossIncome - listOfTaxthresholdService.getTaxthreshold(i).getTaxMax();
			while (i != lastIndex && rangeCheck>0 && calc>0)
			{
				rangeCheck = grossIncome - listOfTaxthresholdService.getTaxthreshold(i+1).getTaxMax();	
				break;
			}
			
			while(i==lastIndex)
			{
				rangeCheck=-1;
				break;
			}
			double taxCent = listOfTaxthresholdService.getTaxthreshold(i).getTaxCent();
			int taxLump = listOfTaxthresholdService.getTaxthreshold(i).getTaxLump();
			
			while(calc>0 && rangeCheck<0)
			{
				double tempGross = (double) calc*taxCent;
	            incomeTax = (int) Math.round(tempGross)+taxLump;
	            break;
			}
		}
		return incomeTax;
	}
}
