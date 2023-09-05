package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class is responsible for generating the Extent Reports.
 */
public class ExtentReportManager implements ITestListener {
	
	// Instance variables for report generation
	private ExtentSparkReporter sparkReporter;
	private ExtentReports extent;
	private ExtentTest test;
	private String reportName;

	/**
	 * This method is invoked when the Test is started. It sets up the Extent Reports.
	 */
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + timeStamp + ".html";

		String path = System.getProperty("user.dir") + "/reports/";

		try {
			sparkReporter = new ExtentSparkReporter(path + reportName);
		} catch (Exception e) {
			System.err.println("Error initializing spark reporter: " + e.getMessage());
			return;
		}

		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
	}

	/**
	 * This method is invoked when a Test is successful. It logs the success in the report.
	 */
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	/**
	 * This method is invoked when a Test fails. It logs the failure and the exception in the report.
	 */
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	/**
	 * This method is invoked when a Test is skipped. It logs the skip and the exception in the report.
	 */
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	/**
	 * This method is invoked when the Test is finished. It flushes the report data to the file.
	 */
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	/**
	 * This method is invoked when a Test is started. Currently, it does nothing.
	 */
	public void onTestStart(ITestResult result) {
		// No implementation needed
	}

	/**
	 * This method is invoked when a Test fails but is within success percentage. Currently, it does nothing.
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// No implementation needed
	}
}