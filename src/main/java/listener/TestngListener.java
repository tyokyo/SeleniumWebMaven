package listener;


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
	}
}