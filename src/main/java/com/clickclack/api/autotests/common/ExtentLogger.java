package com.clickclack.api.autotests.common;

import com.relevantcodes.extentreports.LogStatus;

public class ExtentLogger {

    public static void INFO(String log) {
        try {
            ExtentManager.getCurrentTestMethod().log(LogStatus.INFO, log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
