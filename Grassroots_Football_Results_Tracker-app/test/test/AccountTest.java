package test;

import org.junit.Test;

import core.Account;

public class AccountTest {

	
	@Test
	public void TestAccountCreation() {
		new Account("Jamie", "Wiggins1", "Manager");
		new Account("Joe", "Joe1", "Admin");
		new Account("Ben", "Wig1", "Manager");
	}
	
	/**
	 * This test method tests whether an IllegalArgumentException is thrown when a
	 * null value for role is inputted when the other values are all correct
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestInvalidusername() {
		new Account("1234", "Wiggins1", "Manager");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestInvalidPassword() {
		new Account("Jamie", "1234", "Admin");
	}
}
