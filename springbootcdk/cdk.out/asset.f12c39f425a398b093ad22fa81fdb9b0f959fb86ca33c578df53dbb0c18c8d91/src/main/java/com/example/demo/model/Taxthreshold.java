package com.example.demo.model;

public class Taxthreshold {
	private int taxMaxOfEachStages;
	private double taxCentForOverDollar;
	private int taxLumpBeforeCurrentStage;
	
	public Taxthreshold(int taxMax , double taxCent, int taxLump) 
	{
		taxMaxOfEachStages = taxMax;
		taxCentForOverDollar = taxCent;
		taxLumpBeforeCurrentStage = taxLump;
	}
	
	public void setTaxMax(int taxMax)
	{
		taxMaxOfEachStages=taxMax;
	}
	
	
	public void setTaxCent(double taxCent)
	{
		taxCentForOverDollar = taxCent;
	}
	
	public void setTaxLump(int taxLump)
	{
		taxLumpBeforeCurrentStage = taxLump;
	}
	
	public int getTaxMax()
	{
		return taxMaxOfEachStages;
	}
	
	
	public double getTaxCent()
	{
		return taxCentForOverDollar;
	}
	
	public int getTaxLump()
	{
		return taxLumpBeforeCurrentStage;
	}
	
}
