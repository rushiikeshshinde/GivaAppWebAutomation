package com.giva.qa.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.giva.qa.util.TestUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;

    public static Logger logger = Logger.getLogger(TestBase.class);
    public ExtentReports extent = new ExtentReports();
    public ExtentSparkReporter spark =new ExtentSparkReporter("D:\\WebAutomation\\GivaTestAutomation_Trial\\test_output\\ExtentReport.html");

//    public static String sheetName = "D:\\WebAutomation\\GivaTestAutomation_Trial\\src\\main\\java\\com\\giva\\qa\\testdata\\GivaTestData.xlsx";

    public TestBase() {

        PropertyConfigurator.configure("D:\\WebAutomation\\GivaTestAutomation_Trial\\resources\\Log4j.properties");

        try{
            prop = new Properties();
            FileInputStream ip = new FileInputStream("D:\\WebAutomation\\GivaTestAutomation_Trial\\src\\main\\java\\com\\giva\\qa\\config\\config.properties");
            prop.load(ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void initialization(){
        logger.info("Browser Name: " + prop.getProperty("browser"));
        String browser = prop.getProperty("browser");
        if(browser.equals("chrome")){
            driver = new ChromeDriver();
        }
        else if(browser.equals("FF")){
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        logger.info("Application URL : "+ prop.getProperty("url"));
        driver.get(prop.getProperty("url"));
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }



}
