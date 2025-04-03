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
	
	
}
