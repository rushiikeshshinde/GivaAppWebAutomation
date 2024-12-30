package com.giva.qa.listener;

import com.aventstack.extentreports.ExtentTest;
import com.giva.qa.base.TestBase;
import com.giva.qa.util.Utility;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;


public class TestListener extends TestBase implements ITestListener {

    Utility utility;


    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println(result.getMethod().getMethodName()+ " :onTestStart");
        extent.attachReporter(spark);
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        System.out.println(result.getMethod().getMethodName()+" :onTestSkipped");
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        test.skip("Test method Skipped..");
        extent.flush();

    }



    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println(result.getMethod().getMethodName()+" :onTestSuccess");
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        test.pass("Test successfully executed");
        extent.flush();

    }



    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println(result.getMethod().getMethodName()+  "On Test Failure");

        ExtentTest test = extent.createTest(result.getMethod().getMethodName()).assignAuthor("Rushikesh").assignDevice("Windows 10,Chrome");

        String failcase =result.getMethod().getMethodName();


        utility=new Utility();
        try {
            Utility.failedTest(failcase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath("D:\\WebAutomation\\GivaTestAutomation_Trial\\Screenshots\\" + result.getMethod().getMethodName() + ".png");
        extent.flush();

    }




}