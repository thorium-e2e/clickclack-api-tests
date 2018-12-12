package com.clickclack.api.autotests.common;

import com.relevantcodes.extentreports.LogStatus;

public class ExtentLogger {

    public static void INFO(String log) {
        try {
            ExtentManager.getCurrentTestMethod().log(LogStatus.INFO, log
                    .replace("\n", "<br/>").replace(" ", "&nbsp;"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
