package com.github.jbreno.aula.entities;

import com.github.jbreno.aula.entites.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountTests {
	@Test
	public void depositShouldIncreaseBalanceWhenPositiveAmount() {
		double amount = 200.0;
		double expectedValue = 196.0;
		Account acc = new Account(1L, 0.0);

		acc.deposit(amount);

		Assertions.assertEquals(expectedValue, acc.getBalance());
	}

}
