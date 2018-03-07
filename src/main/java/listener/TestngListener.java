package listener;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.uncommons.reportng.Reporters;

import util.TakeScreen;

public class TestngListener extends TestListenerAdapter {
	private static String SEPERATE="/";
	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		Reporters.logDebug(true,tr.getTestClass().getName()+SEPERATE+tr.getMethod().getMethodName());
		TakeScreen.takeScreenShotWithDraw("FAIL");
		//VP.takeScreenShot(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		Reporters.logDebug(true,tr.getTestClass().getName()+SEPERATE+tr.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		Reporters.logDebug(true,tr.getTestClass().getName()+SEPERATE+tr.getMethod().getMethodName());
		//takeScreenShot(tr);
		//Reporters.logInfo(tr.getName() + " Success");
		//takeScreenShot(tr);
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		Reporters.logDebug(true,tr.getTestClass().getName()+SEPERATE+tr.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		Reporters.logDebug(true,testContext.getName());

		// List of test results which we will delete later
		ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		// collect all id's from passed test
		Set<Integer> passedTestIds = new HashSet<Integer>();
		for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
			Reporters.log("PassedTests = " + passedTest.getName());
			passedTestIds.add(getId(passedTest));
		}

		// Eliminate the repeat methods
		Set<Integer> skipTestIds = new HashSet<Integer>();
		for (ITestResult skipTest : testContext.getSkippedTests().getAllResults()) {
			Reporters.log("skipTest = " + skipTest.getName());
			// id = class + method + dataprovider
			int skipTestId = getId(skipTest);

			if (skipTestIds.contains(skipTestId) || passedTestIds.contains(skipTestId)) {
				testsToBeRemoved.add(skipTest);
			} else {
				skipTestIds.add(skipTestId);
			}
		}

		// Eliminate the repeat failed methods
		Set<Integer> failedTestIds = new HashSet<Integer>();
		for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
			Reporters.log("failedTest = " + failedTest.getName());
			// id = class + method + dataprovider
			int failedTestId = getId(failedTest);

			// if we saw this test as a failed test before we mark as to be
			// deleted
			// or delete this failed test if there is at least one passed
			// version
			if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId) ||
					skipTestIds.contains(failedTestId)) {
				testsToBeRemoved.add(failedTest);
			} else {
				failedTestIds.add(failedTestId);
			}
		}

		// finally delete all tests that are marked
		for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator.hasNext();) {
			ITestResult testResult = iterator.next();
			if (testsToBeRemoved.contains(testResult)) {
				Reporters.log("Remove repeat Fail Test: " + testResult.getName());
				iterator.remove();
			}
		}
	}
	private int getId(ITestResult result) {
		int id = result.getTestClass().getName().hashCode();
		id = id + result.getMethod().getMethodName().hashCode();
		id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
		return id;
	}
}