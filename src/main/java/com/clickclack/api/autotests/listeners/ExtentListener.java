package com.clickclack.api.autotests.listeners;

import com.clickclack.api.autotests.common.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;

import java.util.Arrays;

import static com.clickclack.api.autotests.common.ExtentManager.getCurrentTestClass;
import static org.testng.ITestResult.*;

public class ExtentListener implements IClassListener, ISuiteListener, IInvokedMethodListener {

    // SUITE Listeners

    public void onStart(ISuite suite) {
        ExtentManager.setSuiteReport(new ExtentReports("./target/ExtentReports/" + suite.getName() + ".html"));
    }

    public void onFinish(ISuite suite) {
        ExtentManager.getSuiteReport().flush();
    }

    // CLASS Listeners

    public void onBeforeClass(ITestClass testClass) {
        ExtentManager.setCurrentTestClass(ExtentManager.getSuiteReport().startTest(testClass.getName()));
    }

    public void onAfterClass(ITestClass testClass) {
        ExtentManager.getSuiteReport().endTest(getCurrentTestClass());
    }

    // METHOD INVOCATION Listeners

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        String paramName = Arrays.asList(testResult.getParameters()).toString();
        ExtentManager.setCurrentTestMethod(ExtentManager.getSuiteReport().startTest(method.getTestMethod().getMethodName() + " - " + method.getTestMethod().getCurrentInvocationCount() + 1 + " - " + paramName));
        getCurrentTestClass().appendChild(ExtentManager.getCurrentTestMethod());
        ExtentManager.getCurrentTestMethod().setDescription(method.getTestMethod().getDescription());
        ExtentManager.getCurrentTestMethod().assignAuthor("Adrian Pothuaud");
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // ...
        int status = testResult.getStatus();
        /*
          int CREATED = -1;
          int SUCCESS = 1;
          int FAILURE = 2;
          int SKIP = 3;
          int SUCCESS_PERCENTAGE_FAILURE = 4;
          int STARTED = 16;
         */
        switch (status) {
            case SKIP:
                ExtentManager.getCurrentTestMethod().log(LogStatus.SKIP, "Skipped");
                break;
            case FAILURE:
                ExtentManager.getCurrentTestMethod().log(LogStatus.FAIL, testResult.getThrowable());
                break;
            case SUCCESS:
                ExtentManager.getCurrentTestMethod().log(LogStatus.PASS, "Passed");
                break;
            case SUCCESS_PERCENTAGE_FAILURE:
                ExtentManager.getCurrentTestMethod().log(LogStatus.WARNING, "Passed within success percentage");
                break;
        }

        ExtentManager.getSuiteReport().endTest(ExtentManager.getCurrentTestMethod());
    }
}
