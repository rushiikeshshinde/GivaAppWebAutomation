package com.giva.qa.util;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelFileReading {
    public static FileInputStream fi;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static String link = "D:\\WebAutomation\\GivaTestAutomation_Trial\\src\\main\\java\\com\\giva\\qa\\testdata\\GivaTestData.xlsx";

    public static String[][] getData(String sheetName) throws IOException {
        fi = new FileInputStream(link);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);

        List<String[]> dataList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        int totalRows = ws.getLastRowNum(); // Total number of rows in the sheet

        for (int i = 1; i <= totalRows; i++) {
            row = ws.getRow(i);
            if (row == null || isRowEmpty(row, formatter)) continue; // Skip empty rows

            int totalCols = row.getLastCellNum(); // Total columns in the current row
            List<String> rowData = new ArrayList<>();

            for (int j = 0; j < totalCols; j++) {
                cell = row.getCell(j);
                if (cell == null || isCellEmpty(cell, formatter)) continue; // Skip empty cells
                rowData.add(formatter.formatCellValue(cell).trim());
            }

            if (!rowData.isEmpty()) {
                dataList.add(rowData.toArray(new String[0])); // Add valid row data
            }
        }

        wb.close();
        fi.close();

        // Convert List to Array
        return dataList.toArray(new String[0][0]);
    }

    public static boolean isRowEmpty(XSSFRow row, DataFormatter formatter) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            XSSFCell cell = row.getCell(i);
            if (cell != null && !formatter.formatCellValue(cell).trim().isEmpty()) {
                return false; // Row contains data
            }
        }
        return true; // Row is empty
    }

    public static boolean isCellEmpty(XSSFCell cell, DataFormatter formatter) {
        return formatter.formatCellValue(cell).trim().isEmpty();
    }

    @DataProvider(name = "LoginData")
    public static Object[][] loginData() throws IOException {
        return getData("Sheet1");
    }

    @DataProvider(name = "RegisterData")
    public static Object[][] RegisterData() throws IOException {
        return getData("Sheet2");
    }

    @DataProvider(name = "CheckoutGuestData")
    public static Object[][] CheckoutGuestData() throws IOException {
        return getData("Sheet3");
    }

    @DataProvider(name = "CheckoutUserData")
    public static Object[][] CheckoutUserData() throws IOException {
        return getData("Sheet4");
    }
}
