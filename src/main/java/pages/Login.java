package pages;

import dataprovider.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Helper;

import java.time.Duration;

public class Login {

    WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    By userName = By.name("email1");
    By passWord = By.name("password1");
    By signIn = By.xpath("//button[contains(@type,'submit')]");

    By userNotExist = By.xpath("//h2[contains(@class,'error')]");

    By welcomeText = By.xpath("//h4[@class='welcomeMessage']");

    public Dashboard loginToApplication(String uname, String pass, String loginmessage) {
        driver.findElement(userName).sendKeys(uname);
        driver.findElement(passWord).sendKeys(pass);
//        Helper.waitForSeconds(Integer.parseInt(String.valueOf(ConfigReader.getProperty("explicitwait"))));
        driver.findElement(signIn).click();
        Helper.waitForSeconds(Integer.parseInt(String.valueOf(ConfigReader.getProperty("explicitwait"))));
        if (driver.getCurrentUrl().contains("login")) {
            if (driver.findElement(userNotExist).getText().contains(loginmessage)) {
                System.out.println("Info LOG:" + loginmessage + "is displayed");
            }
        } else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(String.valueOf(ConfigReader.getProperty("explicitwait")))));
            if (wait.until(ExpectedConditions.textToBePresentInElementLocated(welcomeText, loginmessage))) {
                System.out.println("INFO LOG:" + loginmessage);
            } else {
                System.out.println("Login Failed Due to Techinal Reason");
            }
        }
        return new Dashboard(driver);
    }

    public Dashboard loginToApplication(String uname, String pass, String loginmessage, String message) {
        driver.findElement(userName).sendKeys(uname);
        driver.findElement(passWord).sendKeys(pass);
        driver.findElement(signIn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(String.valueOf(ConfigReader.getProperty("explicitwait")))));
        if (wait.until(ExpectedConditions.textToBePresentInElementLocated(welcomeText, loginmessage))) {
            System.out.println("INFO LOG:" + loginmessage);
        } else {
            System.out.println("Login Failed Due to Techinal Reason");
        }

        System.out.println("LOG INFO:"+message);

        return new Dashboard(driver);
    }
}
