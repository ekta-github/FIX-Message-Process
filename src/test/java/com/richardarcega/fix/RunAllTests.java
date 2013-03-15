package com.richardarcega.fix;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AppTest.class })
public class RunAllTests
{
	public static void main(final String[] args)
	{
		// Run all JUnit tests programmatically
		final Result result = JUnitCore.runClasses(AppTest.class, AppTestQuickFixEngine.class);
		for ( final Failure failure : result.getFailures() )
		{
			System.out.println( failure.toString() );
		}
	}
}