package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.demo.model.Validation;

public class ValidationUnitTest {
	
	    Validation validation = new Validation();

	/*    @Test
	    public void validInputStringCannotBeNull() {
	        assertThrows(NullPointerException.class, () -> validation.acceptStringInput(null));
	    }

	    @Test
	    public void validInputIntegerCannotBeNull() {
	        assertThrows(NullPointerException.class, () -> validation.acceptIntegerInput(null));
	    }

	    @Test
	    public void inputStringCannotBeEmpty() {
	        assertThrows(NullPointerException.class, () -> validation.acceptStringInput(""));
	    }

	    @Test
	    public void inputStringCannotBeBlank() {
	        assertThrows(NullPointerException.class, () -> validation.acceptStringInput(" "));
	    }

	    @Test
	    public void checkValidationNotEmptyInputString() {
	        boolean answer = validation.checkStringNotEmptyOrBlank("Cheline");
	        assertTrue(answer);
	    }

	    @Test
	    public void checkValidationEmptyInputString() {
	        boolean answer = validation.checkStringNotEmptyOrBlank(" ");
	        assertFalse(answer);
	    }

	    @Test
	    public void checkValidationInputStringShouldNotContainsNumber() {
	        boolean answer = validation.checkStringNotContainsNumber("abc123");
	        assertFalse(answer);
	    }

	    @Test
	    public void checkValidationInputIntegerWithinRange() {
	        boolean answer = validation.checkIntegerWithinRange(51, 0, 50);
	        assertFalse(answer);
	    }

	    @Test
	    public void checkValidationInputStringLength() {
	        boolean answer = validation.checkStringLength("month", "asdasd");
	        assertFalse(answer);
	    }

	    @Test
	    public void checkCorrectReturnMonth() {
	        String answer = validation.validMonth("Mar");
	        assertEquals(answer,"01 Mar - 31 Mar");
	    }

	    @Test
	    public void checkValidationInputStringIsMonth() {
	        boolean answer = validation.checkInputIsMonth("mar");
	        assertTrue(answer);
	    }*/

	}
