package com.example.demo.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.Payslip;
import com.example.demo.service.PayslipService;
import com.example.demo.service.PayslipServiceImpl;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;



@RestController
@ComponentScan
public class EmployeeController {
	@Autowired
	private PayslipService payslipService = new PayslipServiceImpl();
	
	@PostMapping(value="/postpayslip", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONArray response(@RequestBody String payload){
		
		JSONArray jArray = (JSONArray) JSONValue.parse(payload);
		Payslip payslip=new Payslip();
		LocalDate currentdate = LocalDate.now();
		
		ArrayList<Employee> employee = new ArrayList<Employee>();
		ArrayList<Payslip> emppayslip = new ArrayList<Payslip>();
		
		
		for(int i = 0 ; i < jArray.size() ; i++)
		{
			JSONObject jObj = (JSONObject) jArray.get(i);
			String firstName = jObj.get("firstName").toString();
			String lastName = jObj.get("lastName").toString();
			int annualSalary = Integer.parseInt(jObj.get("annualSalary").toString());		
			int superRate = Integer.parseInt(jObj.get("superRate").toString());
			int paymentMonth = Integer.parseInt(jObj.get("paymentMonth").toString());
			Employee employeeadd = new Employee(firstName,lastName,annualSalary,superRate,paymentMonth);
			employee.add(employeeadd);
		}
		
		for(int i = 0 ; i < employee.size() ; i++)
		{
			
	        payslip.setGrossIncome(employee.get(i).getAnnualSalary());
	        payslip.setSuperAmount(employee.get(i).getSuperRate(),payslip.getGrossIncome());
	        payslip.setIncomeTax(payslipService.calculateIncomeTax(payslip.getGrossIncome()));
	        payslip.setNetIncome(payslip.getGrossIncome(), payslip.getIncomeTax());
	        Month currentMonth = currentdate.getMonth();
	        int lastDay = Calendar.getInstance().getActualMaximum(Calendar.DATE);
	        String lastD = String.valueOf(lastDay);
	        String fromDate="01 "+ currentMonth;
	        String toDate = lastD + " " + currentMonth;
	        payslip = new Payslip(fromDate,toDate,payslip.getIncomeTax(),payslip.getNetIncome(),payslip.getSuperAmount(),payslip.getGrossIncome());
	        emppayslip.add(payslip);	        
		}
		
		JSONObject obj = new JSONObject();
		JSONObject employeeobj = new JSONObject();
		JSONObject employeepayobj = new JSONObject();
		obj.put("employee", employeeobj);
		obj.put("payslip",employeepayobj);
	
		JSONArray result = new JSONArray();
	
		for(int i = 0 ; i < employee.size() ; i++)
		{	
			employeeobj.put("firstName",employee.get(i).getFirstName());
			employeeobj.put("lastName",employee.get(i).getLastName());
			employeeobj.put("annualSalary",employee.get(i).getAnnualSalary());
			employeeobj.put("paymentMonth",employee.get(i).getPaymentMonth());
			employeeobj.put("superRate",employee.get(i).getSuperRate());
			employeepayobj.put("fromDate", emppayslip.get(i).getFromDate());
			employeepayobj.put("toDate", emppayslip.get(i).getToDate());
			employeepayobj.put("grossIncome", emppayslip.get(i).getGrossIncome());
			employeepayobj.put("superannuation", emppayslip.get(i).getSuperAmount());
			employeepayobj.put("netIncome", emppayslip.get(i).getNetIncome());
			result.add(obj);
		}
		
		
	   return result;
	}
	
	

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome cg";
	}	
}