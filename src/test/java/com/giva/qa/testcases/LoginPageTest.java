package com.giva.qa.testcases;

import com.giva.qa.listener.TestListener;
import com.giva.qa.pages.LoginPage;
import com.giva.qa.util.ExcelFileReading;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginPageTest extends LoginPage {

    LoginPage loginPage;

    public LoginPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void LoginPageTitleTest(){
        String title = loginPage.validateLoginPageTitle();
        Assert.assertEquals(title, "Fine Jewellery | Black Friday Sale | Free Gifts & Gold Offers at GIVA – GIVA Jewellery");
    }

    @Test(priority = 2)
    public void givaLogoImageTest(){
        boolean flag = loginPage.validateLogo();
        Assert.assertTrue(flag);
    }


    @Test(priority = 3)
    public void LoginTest(){
        loginPage.login(prop.getProperty("email"), prop.getProperty("pass"));
    }

    @Test(priority = 4,dataProvider = "LoginData", dataProviderClass = ExcelFileReading.class)
    public void LoginWithMultipleUser(String emails,String pass) {
        loginPage.multipleUserLogin(emails,pass);
    }

    @AfterMethod
    public void tearDown() {
        closeDriver();
    }

}
