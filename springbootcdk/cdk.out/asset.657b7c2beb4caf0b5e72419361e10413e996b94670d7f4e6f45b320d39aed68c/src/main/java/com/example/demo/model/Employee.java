package com.example.demo.model;

public class Employee {
	  String firstName;
	  String lastName;
	  int annualSalary;
	  int superRate;
	  int paymentMonth;

	    
	  public Employee(String first, String last, int annual,int superrate,int month)
	  {
	      firstName = first;
	      lastName = last;
	      annualSalary = annual;
	      superRate = superrate;
	      paymentMonth = month;
	  }

	     public String getFirstName() {
	        return firstName;
	     }

	     public String getLastName() {
	        return lastName;
	     }

	     public int getAnnualSalary() {
	        return annualSalary;
	     }

	     public int getSuperRate() {
	         return superRate;
	     }

	     public int getPaymentMonth() {
	         return paymentMonth;
	     }

	     public void setFirstName(String firstName) {
	        this.firstName = firstName;
	     }

	     public void setLastName(String lastName) {
	         this.lastName = lastName;
	     }

	     public void setAnnualSalary(int annualSalary) {
	         this.annualSalary = annualSalary;
	     }

	     public void setSuperRate(int superRate) {
	     
	         this.superRate = superRate;
	         
	     }

	     public void setPaymentStartDate(int paymentMonth) {
	         this.paymentMonth = paymentMonth;
	     }

	     public void displayEmployee()
	     {
	        
	         System.out.println("First name: " + firstName);
	         System.out.println("Last name: " + lastName);
	         System.out.println("Annual Salary: " + annualSalary);
	         System.out.println("Super Rate: " + superRate);
	         System.out.println("Payment Month: " + paymentMonth);
	     }

	     public String toString()
	     {
	         return firstName + lastName + annualSalary + superRate + paymentMonth;
	     }

	    

	 }