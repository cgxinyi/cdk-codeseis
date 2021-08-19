package com.example.demo.model;

import java.util.ArrayList;

public class ListOfTaxthreshold {
	private ArrayList<Taxthreshold> taxthresholds;
	
	public ListOfTaxthreshold()
	{
		taxthresholds=new ArrayList<Taxthreshold>();
	}
	
	public ListOfTaxthreshold(ArrayList<Taxthreshold> newTaxthreshold) 
	{
		taxthresholds=newTaxthreshold;
	}
	
	public void setTaxthreshold(ArrayList<Taxthreshold> newTaxthreshold)
	{
		taxthresholds = newTaxthreshold;
	}
	
	public ArrayList<Taxthreshold> getListOfTaxthreshold()
	{
		return taxthresholds;
	}
	
	public void addTaxthreshold(int taxMax , double taxCent, int taxLump)
	{
		Taxthreshold newTaxthreshold = new Taxthreshold(taxMax, taxCent, taxLump);
		taxthresholds.add(newTaxthreshold);
	}
	
	public int getNumberOfTaxthreshold()
	{
		return taxthresholds.size();
	}
	
	public Taxthreshold getTaxthreshold(int index)
    {
        return taxthresholds.get(index);
    }
}
