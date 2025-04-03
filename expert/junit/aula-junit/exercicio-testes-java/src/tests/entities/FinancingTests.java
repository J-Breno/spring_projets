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
	public void totalAmountShouldUpdateValueWhenDataIsValid() {
		Financing financing = new Financing(100000.0, 2000.0, 80);
		financing.setTotalAmount(80000.0);
		Assertions.assertEquals(80000.0, financing.getTotalAmount());
		Assertions.assertEquals(2000.0, financing.getIncome());
		Assertions.assertEquals(80, financing.getMonths());
	}
	
	@Test
	public void totalAmountShouldThrowIllegalArgumentExceptionWhenDataIsValid() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing financing = new Financing(100000.0, 2000.0, 80);
			financing.setTotalAmount(120000.0);
		});
	}
}
