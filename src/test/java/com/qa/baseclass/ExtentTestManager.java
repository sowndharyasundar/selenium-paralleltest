package com.qa.baseclass;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	static Map extentTestMap = new HashMap();
    static ExtentReports extent = BaseTest.getReportInstance();
 
    public static ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
 
    public static void endTest() {
    	extent.removeTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }
 
    public static ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}
