package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Login  {

    WebDriver driver;

    public Login(WebDriver driver){
        this.driver = driver;
    }

    By userName = By.name("email1");
    By passWord = By.name("password1");
    By signIn = By.xpath("//button[contains(@type,'submit')]");

    public void loginToApplication(String uname, String pass) {
        driver.findElement(userName).sendKeys(uname);
        driver.findElement(passWord).sendKeys(pass);
        driver.findElement(signIn).click();
    }
}
