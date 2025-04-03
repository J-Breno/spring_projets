package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Financing;

public class FinancingTests {
	@Test
	public void constructorShouldCreateOjetectWhenDataIsValid() {
		Financing financing = new Financing(100000.0, 2000.0, 80);
		Assertions.assertEquals(100000.0, financing.getTotalAmount());
		Assertions.assertEquals(2000.0, financing.getIncome());
		Assertions.assertEquals(80, financing.getMonths());
	}
	
	@Test
	public void constructorShouldThrowIllegalArgumentExceptionWhenDataIsInValid() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing financing = new Financing(100000.0, 2000.0, 20);
		});
	}
	
	@Test
	public void setTotalAmountShouldUpdateValueWhenDataIsValid() {
		Financing financing = new Financing(100000.0, 2000.0, 80);
		financing.setTotalAmount(80000.0);
		Assertions.assertEquals(80000.0, financing.getTotalAmount());
	}
	
	@Test
	public void setTotalAmountShouldThrowIllegalArgumentExceptionWhenDataIsValid() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing financing = new Financing(100000.0, 2000.0, 80);
			financing.setTotalAmount(120000.0);
		});
	}
	
	@Test
	public void setIncomeShouldUpdateValueWhenDataIsValid() {
		Financing financing = new Financing(100000.0, 2000.0, 80);
		financing.setIncome(3000.0);
		Assertions.assertEquals(3000.0, financing.getIncome());
	}
	
	@Test
	public void setIncomeShouldThrowIllegalArgumentExceptionWhenDataIsValid() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing financing = new Financing(100000.0, 2000.0, 80);
			financing.setIncome(1000.0);
		});
	}
	
	@Test
	public void setMonthsShouldUpdateValueWhenDataIsValid() {
		Financing financing = new Financing(100000.0, 2000.0, 80);
		financing.setMonths(100);
		Assertions.assertEquals(100, financing.getMonths());
	}
	
	@Test
	public void setMonthsShouldThrowIllegalArgumentExceptionWhenDataIsValid() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing financing = new Financing(100000.0, 2000.0, 80);
			financing.setMonths(60);
		});
	}
	
	@Test
	public void entryShouldCalculateCorrectlyInputValue() {
		Financing financing = new Financing(100000.0, 2000.0, 80);
		Assertions.assertEquals(20000.0, financing.entry());
	}
	
	@Test
	public void quotaShouldCalculateCorrectlyInputValue() {
		Financing financing = new Financing(100000.0, 2000.0, 80);
		Assertions.assertEquals(1000.0, financing.quota());
	}
}
