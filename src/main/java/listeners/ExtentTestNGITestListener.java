package listeners;

import browserfactory.BrowserFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utility.Helper;


public class ExtentTestNGITestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getExtentReportInstance();
    ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        testThreadLocal.set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = BrowserFactory.getDriverInstance();
        String base64 = Helper.takeScreenShotInBase64(driver);
        testThreadLocal.get().pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BrowserFactory.getDriverInstance();
        String base64 = Helper.takeScreenShotInBase64(driver);
        testThreadLocal.get().fail("Test Failed "+result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThreadLocal.get().skip("Test Skipped "+result.getThrowable().getMessage());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
