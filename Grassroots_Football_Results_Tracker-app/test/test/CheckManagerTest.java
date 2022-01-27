package test;

import org.junit.Test;

import core.Manager;

public class CheckManagerTest {

	@Test
	public void TestAccountCreation() {
		new Manager("Jamie", "Wiggins1", "Manager", "Liverpool");
		new Manager("Joe", "Joe1", "Manager", "Chelsea");
		new Manager("Ben", "Wig1", "Manager", "Arsenal");
	}
}
