package base;

import browserfactory.BrowserFactory;
import dataprovider.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

    protected WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUpBrowser() {
        driver = BrowserFactory.selectBrowser(ConfigReader.getProperty("browser"),ConfigReader.getProperty("url"));
        System.out.println("LogInfo: Application is Up and running");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
        System.out.println("Log OFF : Closing The Browser and Application");
    }
}
