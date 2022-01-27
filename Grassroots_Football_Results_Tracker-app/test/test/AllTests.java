package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountTest.class, AdminTest.class, CheckManagerTest.class, CheckMessageTest.class,
		CheckResultsTest.class, TableDataTest.class, TablePredictionsDataTest.class })
public class AllTests {

}
