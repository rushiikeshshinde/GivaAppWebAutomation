package com.giva.qa.pages;

import com.giva.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    @FindBy(xpath = "(//span[@class=\"small-hide\"])[3]")
    WebElement accountLogo;

    @FindBy(name = "customer[email]")
    WebElement inputEmailId;

    @FindBy(name = "customer[password]")
    WebElement inputPassword;

    @FindBy(xpath = "(//button)[12]")
    WebElement loginButton;

    @FindBy(xpath = "(//span[@class=\"small-hide\"])[3]")
    WebElement wishlistIcon;

    @FindBy(xpath = "(//a[@id=\"btn\"])[1]")
    WebElement startShoppingBtn;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public String validateLoginPageTitle() {
        return driver.getTitle();
    }

    public boolean validateLogo(){
        return accountLogo.isDisplayed();
    }

    public void accountLogoClick() {
        accountLogo.click();
    }

    public void setEmail(String email) {
        inputEmailId.sendKeys(email);
    }

    public void setPassword(String pass) {
        inputPassword.sendKeys(pass);
    }

    public void login(String email, String pass) {
        logger.info("User Login the application");
        accountLogoClick();
        setEmail(email);
        setPassword(pass);
        loginButton.click();

    }

    public void multipleUserLogin(String emailID,String password){
        logger.info("Multiple User login the application");
        accountLogoClick();
        setEmail(emailID);
        setPassword(password);
        loginButton.click();
    }

}
