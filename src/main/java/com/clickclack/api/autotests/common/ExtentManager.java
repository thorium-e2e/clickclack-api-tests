package com.clickclack.api.autotests.common;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentManager {

    private static ExtentReports suiteReport; // root
    private static ExtentTest currentTestClass; // main view expand
    private static ExtentTest currentTestMethod; // a line in report

    public static ExtentReports getSuiteReport() {
        return suiteReport;
    }

    public static void setSuiteReport(ExtentReports report) {
        suiteReport = report;
    }

    public static ExtentTest getCurrentTestClass() {
        return currentTestClass;
    }

    public static void setCurrentTestClass(ExtentTest testClass) {
        currentTestClass = testClass;
    }

    public static ExtentTest getCurrentTestMethod() {
        return currentTestMethod;
    }

    public static void setCurrentTestMethod(ExtentTest testMethod) {
        currentTestMethod = testMethod;
    }
}
