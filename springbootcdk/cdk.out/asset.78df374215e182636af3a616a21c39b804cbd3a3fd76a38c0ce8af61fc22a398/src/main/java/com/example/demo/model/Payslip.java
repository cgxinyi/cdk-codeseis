package com.example.demo.model;

public class Payslip {
    String fromDate;
    String toDate;
    int incomeTax;
    int netIncome;
    int superAmount;
    int grossIncome;
    
    public Payslip()
    {
    	
    }
    
    public Payslip(String fromD,String toD, int income,int net,int superA, int gross)
    {
    	fromDate = fromD;
    	toDate = toD;
    	incomeTax = income;
    	netIncome = net;
    	superAmount = superA;
    	grossIncome = gross;
    }

    public void setGrossIncome(int annualSalary) {
        double temp = (double) annualSalary/12;
        grossIncome = (int) Math.round(temp);
    }

    public void setIncomeTax(int incomeT) {
    	
    	incomeTax=incomeT;
    	
    }

    public void setNetIncome(int grossIncome, int incomeTax) {
        netIncome = grossIncome-incomeTax;
      
    }

    public void setSuperAmount(double superRate, int grossIncome) {
        superRate = superRate / 100;
        double tempAmount = (double) superRate * grossIncome;
        superAmount = (int) Math.round(tempAmount);
  
    }

    public void setFromDate(String date)
    {
    	fromDate = date;
    }
    
    public void setToDate(String date)
    {
    	toDate = date;
    }

    
    public String getFromDate()
    {
    	return fromDate;
    }
    
    public String getToDate()
    {
    	return toDate;
    }
    public int getGrossIncome() {
        return grossIncome;
    }

    public int getIncomeTax() {
        return incomeTax;
    }

    public int getNetIncome() {
        return netIncome;
    }

   

    public int getSuperAmount() {
        return superAmount;
    }


    public void displayPayslip()
    {
       
        System.out.println("From Date: " + fromDate);
        System.out.println("To Date: " + toDate);
        System.out.println("Gross Income: " + grossIncome);
        System.out.println("Income Tax: " + incomeTax);
        System.out.println("Net Income: " + netIncome);
        System.out.println("Super Amount: " + superAmount);
    }

    public String toString()
    {
        return fromDate + toDate + grossIncome + incomeTax + netIncome + superAmount;
    }
}