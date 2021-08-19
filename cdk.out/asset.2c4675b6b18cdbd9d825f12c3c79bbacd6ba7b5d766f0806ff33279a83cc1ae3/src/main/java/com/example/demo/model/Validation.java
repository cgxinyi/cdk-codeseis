package com.example.demo.model;

import java.util.Scanner;

public class Validation {
    public String acceptStringInput(String displayMessage)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(displayMessage);
        String userInput = "";
        boolean valid=false;
        try
        { 
            while(valid==false)
            {
                userInput = input.nextLine();
                valid = checkStringNotEmptyOrBlank(userInput);
            } 
        }
        catch(Exception e)
        {
            System.out.println("Please enter String only!");
        }
        return userInput;
        
    }

    public int acceptIntegerInput(String displayMessage)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(displayMessage);
        int userInput = 0;
        boolean valid=false;

        do{
            try
            {       
                userInput = input.nextInt();
                valid=true;
            }
            catch(Exception e)
            {
                System.out.println("Please enter integer only!");
                valid=false;
                input.next();
            }
        
        }while(valid==false);
        return userInput; 
    } 

    public boolean checkStringNotEmptyOrBlank(String userInput)
    {
        if(userInput.trim().equals("")|| userInput.trim().equals(" "))
        {
                System.out.println("Cannot be empty!");
                return false;
        }
        return true;
    }

    public boolean checkStringNotContainsNumber(String userInput)
    {
        if(userInput.matches(".*\\d.*"))
        {
            System.out.println("Cannot contains number!");
            return false;
        }
    return true;
    }

    public boolean checkIntegerWithinRange(int userInput,int min,int max)
    {
        if(userInput<=min || userInput>=max)
        {
            System.out.println("Please enter the value between " + min + " to " + max);
            return false;
        }
    return true;
    }

    public boolean checkStringLength(String msg, String userInput)
    {
        if(userInput.length()!=3)
        {
            System.out.println("Please enter the " + msg + " to MMM format!");
            return false;
        }
    return true;
    }

    public boolean checkInputIsMonth(String userInput)
    {
        boolean valid = false;
        int num = userInput.toLowerCase().equals("jan")? 1 : -1;
        num = userInput.toLowerCase().equals("feb")? 2 : num;
        num = userInput.toLowerCase().equals("mar")? 3 : num;
        num = userInput.toLowerCase().equals("apr")? 4 : num;
        num = userInput.toLowerCase().equals("may")? 5 : num;
        num = userInput.toLowerCase().equals("jun")? 6 : num;
        num = userInput.toLowerCase().equals("jul")? 7 : num;
        num = userInput.toLowerCase().equals("aug")? 8 : num;
        num = userInput.toLowerCase().equals("sep")? 9 : num;
        num = userInput.toLowerCase().equals("oct")? 10 : num;
        num = userInput.toLowerCase().equals("nov")? 11 : num;
        num = userInput.toLowerCase().equals("dec")? 12 : num;
        
        switch (num) {
        case 1 :valid = true;
        case 2 :valid = true;
        case 3 :valid = true;
        case 4 :valid = true;
        case 5 :valid = true;
        case 6 :valid = true;
        case 7 :valid = true;
        case 8 :valid = true;
        case 9 :valid = true;
        case 10 :valid = true;
        case 11 :valid = true;
        case 12 :valid = true;
        default: valid=false;
        }
       /* if(userInput.toLowerCase().equals("jan"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("feb"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("mar"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("apr"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("may"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("jun"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("jul"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("aug"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("sep"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("oct"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("nov"))
        {
            valid = true;
        }
        else if(userInput.toLowerCase().equals("dec"))
        {
            valid = true;
        }
        else
        {
            System.out.println("Please enter correct MMM format, for example: Jan");
            valid = false;
        }
       */
        return valid;
    }

    public String validMonth(String userInput)
    {
        String month="";
        String payperiod ="";
        
        int num = userInput.toLowerCase().equals("jan")? 1 : -1;
        num = userInput.toLowerCase().equals("feb")? 2 : num;
        num = userInput.toLowerCase().equals("mar")? 3 : num;
        num = userInput.toLowerCase().equals("apr")? 4 : num;
        num = userInput.toLowerCase().equals("may")? 5 : num;
        num = userInput.toLowerCase().equals("jun")? 6 : num;
        num = userInput.toLowerCase().equals("jul")? 7 : num;
        num = userInput.toLowerCase().equals("aug")? 8 : num;
        num = userInput.toLowerCase().equals("sep")? 9 : num;
        num = userInput.toLowerCase().equals("oct")? 10 : num;
        num = userInput.toLowerCase().equals("nov")? 11 : num;
        num = userInput.toLowerCase().equals("dec")? 12 : num;
        
        switch (num) {
        case 1 :
        	month="Jan";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 2 :
        	month="Feb";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 3 :
        	month="Mar";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 4 :
        	month="Apr";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 5 :
        	month="May";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 6 :
        	month="Jun";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 7 :
        	month="Jul";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 8 :
        	month="Aug";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 9 :
        	month="Sep";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 10 :
        	month="Oct";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 11 :
        	month="Nov";
            payperiod ="01 "+ month + " - " + "31 " + month;
        case 12 :
        	month="Dec";
            payperiod ="01 "+ month + " - " + "31 " + month;
        default: payperiod="Please input correct month!";
        }
        
        /*if(userInput.toLowerCase().equals("jan"))
        {
            month="Jan";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("feb"))
        {
            month="Feb";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("mar"))
        {
            month="Mar";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("apr"))
        {
            month="Apr";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("may"))
        {
            month="May";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("jun"))
        {
            month="Jun";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("jul"))
        {
            month="Jul";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("aug"))
        {
            month="Aug";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("sep"))
        {
            month="Sep";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("oct"))
        {
            month="Oct";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("nov"))
        {
            month="Nov";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }
        else if(userInput.toLowerCase().equals("dec"))
        {
            month="Dec";
            payperiod ="01 "+ month + " - " + "31 " + month;
        }*/
        
        return payperiod;
    }
}

