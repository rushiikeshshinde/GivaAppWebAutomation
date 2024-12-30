package com.giva.qa.util;

import com.giva.qa.base.TestBase;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends TestBase {

    public static long PAGE_LOAD_TIME = 120;
    public static long IMPLICIT_WAIT = 35;

    public static String TEST_SHEET_PATH = "D:\\WebAutomation\\GivaTestAutomation_Trial\\src\\main\\java\\com\\giva\\qa\\testdata\\GivaTestData.xlsx";

    public void switchToFrame() {

        driver.switchTo().defaultContent();

    }
}