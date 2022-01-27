package test;

import org.junit.Test;

import core.Admin;

public class AdminTest {

	@Test
	public void TestAccountCreation() {
		new Admin("Jamie", "Wiggins1", "Admin");
		new Admin("Joe", "Joe1", "Admin");
		new Admin("Ben", "Wig1", "Admin");
	}
	
}
